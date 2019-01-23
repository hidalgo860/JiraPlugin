package com.quickbase.jira.src;

import com.atlassian.jira.user.ApplicationUser;
import com.atlassian.jira.plugin.webfragment.contextproviders.AbstractJiraContextProvider;
import com.atlassian.jira.plugin.webfragment.model.JiraHelper;
import com.atlassian.jira.issue.Issue;

import com.quickbase.jira.src.Field;
import java.util.*;

public class IssueExporter extends AbstractJiraContextProvider
{
    /*
        if the param is empty, then the $value will be shown

        need the fid ids and stuff

        //add resolver


     */

    public final static String quickbaseUrl = "https://www.qblab-trunk.corp.quickbase.net/db/";
    public final static String table = "bmn3uuyju";
    public final static String test = "?a=";
    public final static String action = "API_GenAddRecordForm";

    /*
        Fields ids
     */
    public final static String NAME_PARAM = "_fid_32";
    public final static String DESCRIPTION_PARAM = "_fid_33";
    public final static String USER_EMAIL_PARAM = "_fid_46";


    @Override
    public Map getContextMap(ApplicationUser user, JiraHelper jiraHelper) {

        Issue currentIssue = (Issue) jiraHelper.getContextParams().get("issue");

        Map contextMap = new HashMap();
        //add form post link
        String link = buildUrl();
        contextMap.put("issueTrackerLink", link);
        contextMap.put("fieldList", buildIssueParams(currentIssue));

        return contextMap;
    }

    private String buildUrl()
    {
        String url = quickbaseUrl + table + test + action;
        return url;
    }

    private List<Field> buildIssueParams(Issue currentIssue)
    {
        List<Field> fieldList = new ArrayList<Field>();
        //get issue name
        fieldList.add(new Field (NAME_PARAM, currentIssue.toString()));
        //get issue description
        String description = currentIssue.getDescription() == null ? "" : currentIssue.getDescription();
        fieldList.add(new Field (DESCRIPTION_PARAM, description));
        //get assignee email
        ApplicationUser user = currentIssue.getAssigneeUser();
        String email = (user == null ? "" : user.getEmailAddress());
        fieldList.add(new Field (USER_EMAIL_PARAM, email));
        return fieldList;
    }
}