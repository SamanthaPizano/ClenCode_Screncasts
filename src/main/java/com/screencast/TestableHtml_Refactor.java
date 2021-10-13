package com.screencast;

import fitnesse.responders.run.SuiteResponder;
import fitnesse.wiki.*;

public class TestableHtml_Refactor
{
        public static String testableHTML(PageData pageData, boolean includeSuiteSetup) throws Exception
        {
            return new SetupAndTeardownSurrounder(pageData, includeSuiteSetup).surround();
        }

    private static class SetupAndTeardownSurrounder {
        private PageData pageData;
        private boolean includeSuiteSetup;
        private WikiPage wikiPage;
        private String content;
        private PageCrawler crawler;

        public SetupAndTeardownSurrounder(PageData pageData, boolean includeSuiteSetup) {
            this.pageData = pageData;
            this.includeSuiteSetup = includeSuiteSetup;
            this.wikiPage = pageData.getWikiPage();
            this.content = "";
            this.crawler = wikiPage.getPageCrawler();
        }

        public String surround() throws Exception {
            if(isTestPage()) {
                surroundPageWithSetupsAndTeardowns();
            }
            return pageData.getHtml();
        }

        private void surroundPageWithSetupsAndTeardowns() throws Exception {
            content += includeSetups();
            content += pageData.getContent() + "\n";
            content += includeTeardowns();
            pageData.setContent(content);
        }

        private boolean isTestPage() throws Exception {
            return pageData.hasAttribute("Test");
        }

        private String includeTeardowns() throws Exception {
            String teardowns = "";
            teardowns += includeIfInherited("teardown", "TeatDown");
            if(includeSuiteSetup)
                teardowns += includeIfInherited("teardown", SuiteResponder.SUITE_TEARDOWN_NAME);
            return teardowns;
        }

        private String includeSetups() throws Exception {
            String setups = "";
            if(includeSuiteSetup)
                setups += includeIfInherited("setup", SuiteResponder.SUITE_SETUP_NAME);
            setups += includeIfInherited("setup", "SetUp");
            return setups;
        }

        private String includeIfInherited(String mode, String pageName) throws Exception {
            WikiPage page = PageCrawlerImpl.getInheritedPage(pageName, wikiPage);
            if (page != null)
                return includePages(mode, page);
            return "";
        }

        private String includePages(String mode, WikiPage suiteSetup) throws Exception {
            WikiPagePath pagePath = crawler.getFullPath(suiteSetup);
            String pagePathName = PathParser.render(pagePath);
            return String.format("!include -%s .%s\n", mode, pagePathName);
        }
    }
}
