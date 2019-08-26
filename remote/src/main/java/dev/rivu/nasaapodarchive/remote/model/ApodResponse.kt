package dev.rivu.nasaapodarchive.remote.model


import com.google.gson.annotations.SerializedName

data class ApodResponse(
    @SerializedName("copyright")
    val copyright: String? = null, // Sara Wager
    @SerializedName("date")
    val date: String, // 2019-08-23
    @SerializedName("explanation")
    val explanation: String, // Drifting through the Orion Arm of the spiral Milky Way Galaxy, this cosmic cloud by chance echoes the outline of California on the west coast of the United States. Our own Sun also lies within the Milky Way's Orion Arm, only about 1,500 light-years from the California Nebula. Also known as NGC 1499, the classic emission nebula is around 100 light-years long. The California Nebula shines with the telltale reddish glow characteristic of hydrogen atoms recombining with long lost electrons. The electrons have been stripped away, ionized by energetic starlight. Most likely providing the energetic starlight that ionizes much of the nebular gas is the bright, hot, bluish star Xi Persei just to the right of the nebula.  A popular target for astrophotographers, this deep California Nebula image is a 6 panel telecopic mosaic and covers a wide field of view. The nebula lies toward the constellation Perseus, not far from the Pleiades.
    @SerializedName("hdurl")
    val hdUrl: String? = null, // https://apod.nasa.gov/apod/image/1908/NGC1499_mosaic.jpg
    @SerializedName("media_type")
    val mediaType: String, // image
    @SerializedName("service_version")
    val serviceVersion: String, // v1
    @SerializedName("title")
    val title: String, // NGC 1499: The California Nebula
    @SerializedName("url")
    val url: String // https://apod.nasa.gov/apod/image/1908/NGC1499_mosaic1024.jpg
)