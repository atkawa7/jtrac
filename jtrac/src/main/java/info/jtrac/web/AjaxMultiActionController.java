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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

/**
 * Spring MultiActionController that handles Ajax calls
 */
public class AjaxMultiActionController extends AbstractMultiActionController {
    
    public ModelAndView ajaxTestHandler(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("ajaxTestHandler called");
        applyCacheSeconds(response, 0, true);
        return new ModelAndView("ajax_test");
    } 
    
}
