<%@ include file="/WEB-INF/jsp/header.jsp" %>

<form method="post" action="<c:url value='/flow'/>">

<span class="info">Space Details</span>

<c:if test="${space.id > 0}"><input type="submit" name="_eventId_delete" value="Delete"/></c:if>

<br/><br/>

    <table class="jtrac">
        <tr>
            <td class="label">
                Display Name
                <font color="red">*</font>
            </td>
            <spring:bind path="space.name">
                <td>
                    <input name="${status.expression}" value="${status.value}" id="focus"/>
                    <span class="error">${status.errorMessage}</span>
                </td>
            </spring:bind>
        </tr>         
        <tr>
            <td class="label">
                Space Key (short name)
                <font color="red">*</font>
            </td>
            <spring:bind path="space.prefixCode">
                <td>
                    <input name="${status.expression}" value="${status.value}" size="10"/>
                    <span class="error">${status.errorMessage}</span>
                </td>
            </spring:bind>
        </tr>       
        <tr>
            <td class="label">Description</td>
            <spring:bind path="space.description">
                <td>
                    <textarea name="${status.expression}" rows="3" cols="40">${status.value}</textarea>
                    <span class="error">${status.errorMessage}</span>
                </td>
            </spring:bind>
        </tr>
        <tr>
            <td class="label">Make Public</td>
            <spring:bind path="space.guestAllowed">
                <td>
                    <input type="checkbox" name="${status.expression}" value="true" <c:if test="${status.value}">checked="true"</c:if>/>                
                    Allow Guest (read only) access
                    <input type="hidden" name="_${status.expression}"/>                    
                </td>
            </spring:bind>
        </tr>   
        <tr>
            <td/>
            <td>
                <input type="submit" name="_eventId_submit" value="Next"/>
                <input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}"/>
            </td>
        </tr>
    </table>

    <input type="submit" name="_eventId_cancel" value="Cancel"/>
    
</form>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>