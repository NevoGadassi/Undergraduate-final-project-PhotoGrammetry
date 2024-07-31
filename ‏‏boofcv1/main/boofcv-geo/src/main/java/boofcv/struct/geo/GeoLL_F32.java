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

package boofcv.struct.geo;

import javax.annotation.Generated;
/**
 * Geographic coordinate consisting of latitude (north-south coordinate) and longitude (west-east) .
 *
 * <center>
 * <img src="doc-files/sphere_lat_lon.png"/>
 * </center>
 */
@Generated("boofcv.struct.geo.GeoLL_F64")
public class GeoLL_F32 {
	/**
	 * latitude
	 */
	public float lat;
	/**
	 * longitude
	 */
	public float lon;

	public void setTo( GeoLL_F32 src ) {
		this.lat = src.lat;
		this.lon = src.lon;
	}

	public float getLat() {
		return lat;
	}

	public void setLat( float lat ) {
		this.lat = lat;
	}

	public float getLon() {
		return lon;
	}

	public void setLon( float lon ) {
		this.lon = lon;
	}
}
