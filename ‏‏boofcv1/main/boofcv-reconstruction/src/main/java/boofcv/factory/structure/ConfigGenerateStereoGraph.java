/*
 * Copyright (c) 2021, Peter Abeles. All Rights Reserved.
 *
 * This file is part of BoofCV (http://boofcv.org).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package boofcv.factory.structure;

import boofcv.misc.BoofMiscOps;
import boofcv.struct.Configuration;

/**
 * Configuration for {@link boofcv.alg.structure.GenerateStereoPairGraphFromScene}.
 *
 * @author Peter Abeles
 */
public class ConfigGenerateStereoGraph implements Configuration {
	/** Intended to stop small number of observations causing large swings in score. Larger means more smoothing. */
	public double countSmootherParam = 10.0;

	/** Minimum fraction for common features between the two frames */
	public double minimumCommonFeaturesFrac = 0.5;

	/** If the predicted disparity is above this value the score will not improve. */
	public double targetDisparity = 50.0;

	@Override public void checkValidity() {
		BoofMiscOps.checkTrue(countSmootherParam >= 0.0);
		BoofMiscOps.checkFraction(minimumCommonFeaturesFrac, "Must be from 0 to 1.0");
		BoofMiscOps.checkTrue(targetDisparity > 0);
	}

	public ConfigGenerateStereoGraph setTo( ConfigGenerateStereoGraph src ) {
		this.countSmootherParam = src.countSmootherParam;
		this.minimumCommonFeaturesFrac = src.minimumCommonFeaturesFrac;
		this.targetDisparity = src.targetDisparity;
		return this;
	}
}
