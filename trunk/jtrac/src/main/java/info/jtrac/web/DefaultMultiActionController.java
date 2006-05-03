/*
 * Copyright 2002-2005 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package info.jtrac.web;

import info.jtrac.domain.Config;
import info.jtrac.domain.ItemSearch;
import info.jtrac.util.AttachmentUtils;
import info.jtrac.util.ExcelUtils;
import info.jtrac.util.SvnUtils;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletOutputStream;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.acegisecurity.AuthenticationException;
import org.acegisecurity.ui.AbstractProcessingFilter;
import org.acegisecurity.ui.rememberme.TokenBasedRememberMeServices;
import org.acegisecurity.ui.webapp.AuthenticationProcessingFilter;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;
import org.springframework.webflow.context.servlet.ServletExternalContext;
import org.springframework.webflow.execution.FlowExecution;
import org.springframework.webflow.execution.repository.FlowExecutionKey;
import org.springframework.webflow.execution.repository.FlowExecutionRepository;
import org.springframework.webflow.executor.FlowExecutionKeyFormatter;

public class DefaultMultiActionController extends AbstractMultiActionController {

    public ModelAndView loginHandler(HttpServletRequest request,
            HttpServletResponse response) {
        String message = null;
        if (request.getParameter("error") != null) {
            AuthenticationException ae = (AuthenticationException) WebUtils.getSessionAttribute(request, AbstractProcessingFilter.ACEGI_SECURITY_LAST_EXCEPTION_KEY);
            message = ae.getMessage();
        }
        String loginName = (String) WebUtils.getSessionAttribute(request, AuthenticationProcessingFilter.ACEGI_SECURITY_LAST_USERNAME_KEY);
        Map<String, String> model = new HashMap<String, String>();
        model.put("message", message);
        model.put("loginName", loginName);
        return new ModelAndView("login", model);
    }

    public ModelAndView logoutHandler(HttpServletRequest request,
            HttpServletResponse response) {
        request.getSession().invalidate();
        Cookie terminate = new Cookie(TokenBasedRememberMeServices.ACEGI_SECURITY_HASHED_REMEMBER_ME_COOKIE_KEY, null);
        terminate.setMaxAge(0);
        response.addCookie(terminate);
        return new ModelAndView("logout");
    }

    public ModelAndView svnViewHandler(HttpServletRequest request,
            HttpServletResponse response) {
        String url = request.getParameter("url");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Map<String, Integer> commitsPerCommitter = SvnUtils.getCommitsPerCommitter(SvnUtils.getRepository(url, username, password));
        request.getSession().setAttribute("commitsPerCommitter", commitsPerCommitter);
        return new ModelAndView("svn_view");
    }    

    public ModelAndView attachmentViewHandler(HttpServletRequest request, HttpServletResponse response) throws Exception {
        AttachmentUtils.download(request, response);
        return null;
    }

    public ModelAndView svnCommitsPerCommitterChartHandler(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        Map<String, Integer> commitsPerCommitter = (Map<String, Integer>) request.getSession().getAttribute("commitsPerCommitter");
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Map.Entry<String, Integer> entry : commitsPerCommitter.entrySet()) {
            dataset.addValue(entry.getValue(), "Commits", entry.getKey());
        }
        JFreeChart chart = ChartFactory.createBarChart(null, null, null, dataset, PlotOrientation.VERTICAL, false, false, false);
        response.setContentType("image/jpeg");
        OutputStream out = response.getOutputStream();
        ChartUtilities.writeChartAsJPEG(out, chart, 600, 300);
        out.close();
        return null;
    }

    public ModelAndView userListHandler(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("user_list", "users", jtrac.findAllUsers());
    }

    public ModelAndView spaceListHandler(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("space_list", "spaces", jtrac.findAllSpaces());
    }    

    public ModelAndView configListHandler(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("configMap", jtrac.loadAllConfig());
        model.put("configKeys", Config.getKeys());
        return new ModelAndView("config_list", model);
    }
    
    public ModelAndView itemListExcelHandler(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String flowExecutionKey = request.getParameter("_flowExecutionKey");
        ServletExternalContext ctx = new ServletExternalContext(getServletContext(), request, response);
        FlowExecutionRepository repository = flowExecutionRepositoryFactory.getRepository(ctx);
        FlowExecutionKeyFormatter formatter = new FlowExecutionKeyFormatter();
        FlowExecutionKey key = (FlowExecutionKey) formatter.parseValue(flowExecutionKey, null);
        FlowExecution flow = repository.getFlowExecution(key);        
        ItemSearch itemSearch = (ItemSearch) flow.getActiveSession().getScope().get("itemSearch");
        int pageSize = itemSearch.getPageSize();
        itemSearch.setPageSize(-1);
        ExcelUtils eu = new ExcelUtils(jtrac.findItems(itemSearch), itemSearch);
        itemSearch.setPageSize(pageSize);
        response.setContentType("application/unknow");
        response.setHeader("Content-Disposition", "inline;filename=jtrac-export.xls");
        ServletOutputStream out = response.getOutputStream();
        eu.exportToExcel().write(out);
        out.flush();        
        return null;        
    }       
    
}
