package com.wceng.xbrowser.util;

import junit.framework.TestCase;

import org.junit.Test;

public class UrlUtilTest extends TestCase {

    @Test
    public void testIsUrl(){
        boolean b = UrlUtil.isUrl("www.how2j.com")
                &&UrlUtil.isUrl("how2j.com")
                &&UrlUtil.isUrl("how2j.cn")
                &&UrlUtil.isUrl("https://how2j.com")
                &&UrlUtil.isUrl("www.how2j.cn")
                &&UrlUtil.isUrl("http://www.how2j.com");
        assertTrue(b);
    }
}