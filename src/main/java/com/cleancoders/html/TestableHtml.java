package com.cleancoders.html;

import fitnesse.responders.run.SuiteResponder;
import fitnesse.wiki.*;

public class TestableHtml {

    public String testableHtml(PageData pageData, boolean includeSuiteSetup) throws Exception {
        return new SetupTeardownSurrounder(pageData, includeSuiteSetup).surround();
    }

    private static class SetupTeardownSurrounder {
        private final PageData pageData;
        private final boolean includeSuiteSetup;
        private final WikiPage wikiPage;
        private final PageCrawler pageCrawler;
        private String content;

        public SetupTeardownSurrounder(PageData pageData, boolean includeSuiteSetup) {
            this.pageData = pageData;
            this.includeSuiteSetup = includeSuiteSetup;
            wikiPage = pageData.getWikiPage();
            content = "";
            pageCrawler = wikiPage.getPageCrawler();
        }

        public String surround() throws Exception {
            if (isTestPage())
                surroundPageWithSetupsAndTeardowns();
            return pageData.getHtml();
        }

        private boolean isTestPage() throws Exception {
            return pageData.hasAttribute("Test");
        }

        private void surroundPageWithSetupsAndTeardowns() throws Exception {
            content += includeSetups();
            content += pageData.getContent();
            content += includeTeardowns();
            pageData.setContent(content);
        }

        private String includeSetups() throws Exception {
            String setups = "";
            if (includeSuiteSetup)
                setups += includeIfInherited("setup", SuiteResponder.SUITE_SETUP_NAME);
            setups += includeIfInherited("setup", "SetUp");
            return setups;
        }

        private String includeTeardowns() throws Exception {
            String teardowns = "";
            teardowns += includeIfInherited("teardown", "TearDown");
            if (includeSuiteSetup)
                teardowns += includeIfInherited("teardown", SuiteResponder.SUITE_TEARDOWN_NAME);
            return teardowns;
        }

        private String includeIfInherited(String mode, String pageName) throws Exception {
            WikiPage page = PageCrawlerImpl.getInheritedPage(pageName, wikiPage);
            return (page != null) ? includePage(mode, page) : "";
        }

        private String includePage(String mode, WikiPage page) throws Exception {
            WikiPagePath pagePath = pageCrawler.getFullPath(page);
            String pagePathName = PathParser.render(pagePath);
            return String.format("!include -%s .%s%n", mode, pagePathName);
        }
    }
}
