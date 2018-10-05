/*-
 * #%L
 * Bobcat
 * %%
 * Copyright (C) 2018 Cognifide Ltd.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package com.cognifide.qa.bb.aem.core.siteadmin.aem64;

import com.cognifide.qa.bb.aem.core.siteadmin.SiteAdminAction;
import com.cognifide.qa.bb.aem.core.siteadmin.SiteAdminController;
import com.google.inject.Inject;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * SiteAdminController for AEM 6.4
 */
public class AemSiteAdminController implements SiteAdminController {

  @Inject
  private Map<String, SiteAdminAction> siteAdminActions;

  @Override
  public Set<String> getAvailableActions() {
    return siteAdminActions.keySet();
  }

  @Override
  public SiteAdminAction getSiteAdminAction(String action) {
    return siteAdminActions.get(action);
  }

}