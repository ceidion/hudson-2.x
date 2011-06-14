/**
 * The MIT License 
 * 
 * Copyright (c) 2010-2011 Sonatype, Inc. All rights reserved.
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
    
package org.eclipse.hudson.inject.injecto;

import hudson.model.Describable;
import hudson.model.Descriptor;

import org.eclipse.hudson.inject.SmoothieTestSupport;
import org.eclipse.hudson.inject.injecto.Injectable;
import org.junit.Test;

import javax.inject.Inject;

import static org.junit.Assert.assertNotNull;

/**
 * Tests for {@link Describable} types.
 */
public class DescribableTest
    extends SmoothieTestSupport
{
    @Test
    public void testDescribable() {
        Thing thing = new DescribableThing();
        assertNotNull(thing);
        assertNotNull(thing.component);
    }

    public static class DescribableThing
        extends Thing
        implements Describable<DescribableThing>
    {
        @Inject
        public void setComponent(SimpleComponent component) {
            super.setComponent(component);
        }

        public Descriptor<DescribableThing> getDescriptor() {
            return null;
        }
    }

    @Test
    public void testDescribableWithImplementsInjectable() {
        Thing thing = new DescribableThing2();
        assertNotNull(thing);
        assertNotNull(thing.component);
    }

    public static class DescribableThing2
        extends Thing
        implements Describable<DescribableThing>, Injectable
    {
        @Inject
        public void setComponent(SimpleComponent component) {
            super.setComponent(component);
        }

        public Descriptor<DescribableThing> getDescriptor() {
            return null;
        }
    }
}
