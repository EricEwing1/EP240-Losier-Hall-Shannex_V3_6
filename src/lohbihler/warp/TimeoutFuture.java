/*
 * Copyright (c) 2017, Matthew Lohbihler
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package lohbihler.warp;

import java.util.concurrent.CancellationException;

public interface TimeoutFuture<V> {
    V get() throws CancellationException, InterruptedException, Exception;

    boolean cancel();
}
