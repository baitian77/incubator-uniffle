/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.uniffle.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.apache.uniffle.common.KerberizedHadoopBase;
import org.apache.uniffle.coordinator.metric.CoordinatorMetrics;

public class AccessCandidatesCheckerKerberizedHadoopTest extends KerberizedHadoopBase {

  @BeforeAll
  public static void beforeAll() throws Exception {
    testRunner = AccessCandidatesCheckerKerberizedHadoopTest.class;
    KerberizedHadoopBase.init();
  }

  @BeforeEach
  public void setUp() throws Exception {
    CoordinatorMetrics.register();
    initHadoopSecurityContext();
  }

  @AfterEach
  public void afterEach() throws Exception {
    CoordinatorMetrics.clear();
    removeHadoopSecurityContext();
  }

  @Test
  public void test() throws Exception {
    String candidatesFile =  kerberizedHadoop.getSchemeAndAuthorityPrefix() + "/test/access_checker_candidates";
    AccessCandidatesCheckerHadoopTest.createAndRunCases(
        kerberizedHadoop.getSchemeAndAuthorityPrefix(),
        candidatesFile,
        kerberizedHadoop.getFileSystem(),
        kerberizedHadoop.getConf()
    );
  }
}
