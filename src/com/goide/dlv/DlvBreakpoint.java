/*
 * Copyright 2013-2015 Sergey Ignatov, Alexander Zolotov, Mihai Toader, Florin Patan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.goide.dlv;

import com.goide.dlv.rdp.Location;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.concurrency.Promise;
import org.jetbrains.debugger.BreakpointBase;
import org.jetbrains.debugger.BreakpointManager;
import org.jetbrains.debugger.BreakpointTarget;

// mozilla location is ugly, so, we use our implementation
public class DlvBreakpoint extends BreakpointBase<Location> {
  @Nullable
  volatile String actor;

  protected DlvBreakpoint(@NotNull BreakpointTarget target, int line, int column, @Nullable String condition, boolean enabled) {
    super(target, line, column, condition, enabled);
  }

  void setRemoteData(@Nullable String actor, @Nullable Location actualLocation) {
    this.actor = actor;
    setActualLocation(actualLocation);
  }

  @Override
  public boolean isVmRegistered() {
    return actor != null;
  }

  @NotNull
  @Override
  public Promise<Void> flush(@NotNull BreakpointManager breakpointManager) {
    return Promise.DONE;
  }
}