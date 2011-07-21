/*******************************************************************************
 *
 * Copyright (c) 2004-2009 Oracle Corporation.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors: 
*
*    Kohsuke Kawaguchi, Erik Ramfelt
 *     
 *
 *******************************************************************************/ 

package hudson.model;

import com.gargoylesoftware.htmlunit.WebAssert;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import hudson.matrix.MatrixProject;
import hudson.maven.MavenModuleSet;
import org.jvnet.hudson.test.HudsonTestCase;

public class JobPropertyTest extends HudsonTestCase {

    /**
     * Asserts that rfe#2398 is fixed.
     */
    public void testJobPropertySummaryIsShownInMavenModuleSetIndexPage() throws Exception {
        assertJobPropertySummaryIsShownInIndexPage(MavenModuleSet.DESCRIPTOR);
    }
    public void testJobPropertySummaryIsShownInMatrixProjectIndexPage() throws Exception {
        assertJobPropertySummaryIsShownInIndexPage(MatrixProject.DESCRIPTOR);
    }
    public void testJobPropertySummaryIsShownInFreeStyleProjectIndexPage() throws Exception {
        assertJobPropertySummaryIsShownInIndexPage(FreeStyleProject.DESCRIPTOR);
    }

    private void assertJobPropertySummaryIsShownInIndexPage(TopLevelItemDescriptor type) throws Exception {
        JobPropertyImpl jp = new JobPropertyImpl("NeedleInPage");
        Job<?,?> project = (Job<?, ?>) hudson.createProject(type, "job-test-case");
        project.addProperty(jp);
        
        HtmlPage page = new WebClient().goTo("job/job-test-case");
        WebAssert.assertTextPresent(page, "NeedleInPage");
    }
    
    public static class JobPropertyImpl extends JobProperty<Job<?,?>> {
        public static DescriptorImpl DESCRIPTOR = new DescriptorImpl();
        private final String propertyString;
        public JobPropertyImpl(String propertyString) {
            this.propertyString = propertyString;
        }

        public String getPropertyString() {
            return propertyString;
        }

        @Override
        public JobPropertyDescriptor getDescriptor() {
            return DESCRIPTOR;
        }

        @SuppressWarnings("unchecked")        
        private static class DescriptorImpl extends JobPropertyDescriptor {
            @Override
            public boolean isApplicable(Class<? extends Job> jobType) {
                return false;
            }

            @Override
            public String getDisplayName() {
                return "Fake job property";
            }
        }
    }
}