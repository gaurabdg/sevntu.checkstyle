////////////////////////////////////////////////////////////////////////////////
// checkstyle: Checks Java source code for adherence to a set of rules.
// Copyright (C) 2001-2016 the original author or authors.
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public
// License along with this library; if not, write to the Free Software
// Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
////////////////////////////////////////////////////////////////////////////////

package com.github.sevntu.checkstyle.checks.coding;

import static com.github.sevntu.checkstyle.checks.coding.EmptyPublicCtorInClassCheck.MSG_KEY;

import org.junit.Test;

import com.github.sevntu.checkstyle.BaseCheckTestSupport;
import com.puppycrawl.tools.checkstyle.DefaultConfiguration;

public class EmptyPublicCtorInClassCheckTest extends BaseCheckTestSupport {
    private final String message = getCheckMessage(MSG_KEY);

    private DefaultConfiguration checkConfig = createCheckConfig(EmptyPublicCtorInClassCheck.class);

    @Test
    public void testEmptyPublicCtor()
            throws Exception {
        final String[] expected = {
            "5:5: " + message,
        };

        verify(checkConfig, getPath("InputEmptyPublicCtorInClass1.java"), expected);
    }

    @Test
    public void testEmptyPrivateCtor()
            throws Exception {
        final String[] expected = {};

        verify(checkConfig, getPath("InputEmptyPublicCtorInClass2.java"), expected);
    }

    @Test
    public void testEmptyProtectedCtor()
            throws Exception {
        final String[] expected = {};

        verify(checkConfig, getPath("InputEmptyPublicCtorInClass6.java"), expected);
    }

    @Test
    public void testClassWithMultiplePublicCtors()
            throws Exception {
        final String[] expected = {};

        verify(checkConfig, getPath("InputEmptyPublicCtorInClass3.java"), expected);
    }

    @Test
    public void testPublicNotEmptyCtor()
            throws Exception {
        final String[] expected = {};

        verify(checkConfig, getPath("InputEmptyPublicCtorInClass4.java"), expected);
    }

    @Test
    public void testClassWithInnerClasses()
            throws Exception {
        final String[] expected = {
            "5:5: " + message,
            "14:9: " + message,
        };

        verify(checkConfig, getPath("InputEmptyPublicCtorInClass5.java"), expected);
    }

    @Test
    public void testCtorAnnotatedWithAnnotation() throws Exception {
        final DefaultConfiguration config = createCheckConfig(EmptyPublicCtorInClassCheck.class);

        config.addAttribute("ctorAnnotationNames", "com\\.github\\.sevntu\\.checkstyle\\.checks\\.coding\\.AnnotationName");

        final String[] expected = {};

        verify(config, getPath("InputEmptyPublicCtorInClass7.java"), expected);
    }

    @Test
    public void testClassAnnotatedWithAnnotation1() throws Exception {
        final DefaultConfiguration config = createCheckConfig(EmptyPublicCtorInClassCheck.class);

        config.addAttribute("classAnnotationNames",
                "com\\.github\\.sevntu\\.checkstyle\\.checks\\.coding\\.AnnotationName|"
                + "org\\.junit\\.runner\\.RunWith|"
                + "org\\.junit\\.Ignore|"
                + "com\\.github\\.sevntu\\.checkstyle\\.checks\\.coding\\.InputEmptyPublicCtorInClass9\\.InnerAnnotation");

        final String[] expected = {};

        verify(config, getPath("InputEmptyPublicCtorInClass8.java"), expected);
    }

    @Test
    public void testClassAnnotatedWithAnnotation2() throws Exception {
        final DefaultConfiguration config = createCheckConfig(EmptyPublicCtorInClassCheck.class);

        config.addAttribute("classAnnotationNames",
                "org\\.junit\\.runner\\.RunWith|org\\.junit\\.Ignore|");

        final String[] expected = {};

        verify(config, getPath("InputEmptyPublicCtorInClass10.java"), expected);
    }
}