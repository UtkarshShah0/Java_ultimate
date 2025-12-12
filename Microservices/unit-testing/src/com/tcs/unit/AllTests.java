package com.tcs.unit;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ TestCompute.class, TestCompute2.class, TestCompute3.class, TestCompute4.class, TestCompute5.class })
public class AllTests {

}
