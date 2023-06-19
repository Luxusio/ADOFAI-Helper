package io.luxus.adofai.lib

import com.fasterxml.jackson.databind.JsonNode
import io.luxus.adofai.lib.property.*
import io.luxus.adofai.lib.util.requireMin

data class CustomLevelSetting(
    val version: Long,
    val artist: String,
    val specialArtistType: SpecialArtistType,
    val artistPermission: String,
    val song: String,
    val author: String,
    val separateCountdownTime: Toggle,
    val previewImage: String,
    val previewIcon: String,
    val previewIconColor: Color,
    val previewSongStart: Long,
    val previewSongDuration: Long,
    val seizureWarning: Toggle,
    val levelDesc: String,
    val levelTags: String,
    val artistLinks: String,
    val difficulty: Long,
    val requiredMods: List<String>,
    val songFilename: String,
    val bpm: Double,
    val volume: Long,
    val offset: Long,
    val pitch: Long,
    val hitsound: Hitsound,
    val hitsoundVolume: Long,
    val countdownTicks: Long,
    val trackColorType: TrackColorType,
    val trackColor: AlphaColor,
    val secondaryTrackColor: AlphaColor,
    val trackColorAnimDuration: Double,
    val trackColorPulse: TrackColorPulse,
    val trackPulseLength: Long,
    val trackStyle: TrackStyle,
    val trackTexture: String,
    val trackTextureScale: Double,
    val trackGlowIntensity: Double,
    val trackAnimation: TrackAnimation,
    val beatsAhead: Long,
    val trackDisappearAnimation: TrackDisappearAnimation,
    val beatsBehind: Long,
    val backgroundColor: Color,
    val showDefaultBGIfNoImage: Toggle,
    val showDefaultBGTile: Toggle,
    val defaultBGTileColor: AlphaColor, // Tutorial background pattern color
    val defaultBGShapeType: DefaultBGShapeType,
    val defaultBGShapeColor: AlphaColor, // Tutorial background shape color
    val bgImage: String,
    val bgImageColor: Color,
    val parallax: Pair<Double, Double>,
    val bgDisplayMode: BGDisplayModeType,
    val imageSmoothing: Toggle,
    val lockRot: Toggle,
    val loopBG: Toggle,
    val scalingRatio: Long,
    val relativeTo: CameraRelativeTo,
    val position: Pair<Double, Double>,
    val rotation: Double,
    val zoom: Long,
    val pulseOnFloor: Toggle,
    val startCamLowVFX: Toggle,
    val bgVideo: String,
    val loopVideo: Toggle,
    val vidOffset: Long,
    val floorIconOutlines: Toggle,
    val stickToFloors: Toggle,
    val planetEase: Ease,
    val planetEaseParts: Long,
    val planetEasePartBehavior: EasePartBehavior,
    val customClass: String,
    val defaultTextColor: AlphaColor,
    val defaultTextShadowColor: AlphaColor,
    val congratsText: String,
    val perfectText: String,
    val legacyFlash: Boolean,
    val legacyCamRelativeTo: Boolean,
    val legacySpriteTiles: Boolean,
    val unknownProperties: Map<String, JsonNode>,
) {

    init {
        version.requireMin(1)
    }

    fun toBuilder() = Builder()
        .version(version)
        .artist(artist)
        .specialArtistType(specialArtistType)
        .artistPermission(artistPermission)
        .song(song)
        .author(author)
        .separateCountdownTime(separateCountdownTime)
        .previewImage(previewImage)
        .previewIcon(previewIcon)
        .previewIconColor(previewIconColor)
        .previewSongStart(previewSongStart)
        .previewSongDuration(previewSongDuration)
        .seizureWarning(seizureWarning)
        .levelDesc(levelDesc)
        .levelTags(levelTags)
        .artistLinks(artistLinks)
        .difficulty(difficulty)
        .requiredMods(requiredMods)
        .songFilename(songFilename)
        .bpm(bpm)
        .volume(volume)
        .offset(offset)
        .pitch(pitch)
        .hitsound(hitsound)
        .hitsoundVolume(hitsoundVolume)
        .countdownTicks(countdownTicks)
        .trackColorType(trackColorType)
        .trackColor(trackColor)
        .secondaryTrackColor(secondaryTrackColor)
        .trackColorAnimDuration(trackColorAnimDuration)
        .trackColorPulse(trackColorPulse)
        .trackPulseLength(trackPulseLength)
        .trackStyle(trackStyle)
        .trackTexture(trackTexture)
        .trackTextureScale(trackTextureScale)
        .trackGlowIntensity(trackGlowIntensity)
        .trackAnimation(trackAnimation)
        .beatsAhead(beatsAhead)
        .trackDisappearAnimation(trackDisappearAnimation)
        .beatsBehind(beatsBehind)
        .backgroundColor(backgroundColor)
        .showDefaultBGIfNoImage(showDefaultBGIfNoImage)
        .showDefaultBGTile(showDefaultBGTile)
        .defaultBGTileColor(defaultBGTileColor)
        .defaultBGShapeType(defaultBGShapeType)
        .defaultBGShapeColor(defaultBGShapeColor)
        .bgImage(bgImage)
        .bgImageColor(bgImageColor)
        .parallax(parallax)
        .bgDisplayMode(bgDisplayMode)
        .imageSmoothing(imageSmoothing)
        .lockRot(lockRot)
        .loopBG(loopBG)
        .scalingRatio(scalingRatio)
        .relativeTo(relativeTo)
        .position(position)
        .rotation(rotation)
        .zoom(zoom)
        .pulseOnFloor(pulseOnFloor)
        .startCamLowVFX(startCamLowVFX)
        .bgVideo(bgVideo)
        .loopVideo(loopVideo)
        .vidOffset(vidOffset)
        .floorIconOutlines(floorIconOutlines)
        .stickToFloors(stickToFloors)
        .planetEase(planetEase)
        .planetEaseParts(planetEaseParts)
        .planetEasePartBehavior(planetEasePartBehavior)
        .customClass(customClass)
        .defaultTextColor(defaultTextColor)
        .defaultTextShadowColor(defaultTextShadowColor)
        .congratsText(congratsText)
        .perfectText(perfectText)
        .legacyFlash(legacyFlash)
        .legacyCamRelativeTo(legacyCamRelativeTo)
        .legacySpriteTiles(legacySpriteTiles)
        .unknownProperties(unknownProperties)

    class Builder {
        var version: Long = 13
            private set
        var artist: String = ""
            private set
        var specialArtistType: SpecialArtistType = SpecialArtistType.NONE
            private set
        var artistPermission: String = ""
            private set
        var song: String = ""
            private set
        var author: String = ""
            private set
        var separateCountdownTime: Toggle = Toggle.ENABLED
            private set
        var previewImage: String = ""
            private set
        var previewIcon: String = ""
            private set
        var previewIconColor: Color = Color.Builder().rgb(0x00, 0x3f, 0x52).build()
            private set
        var previewSongStart: Long = 0
            private set
        var previewSongDuration: Long = 10
            private set
        var seizureWarning: Toggle = Toggle.DISABLED
            private set
        var levelDesc: String = ""
            private set
        var levelTags: String = ""
            private set
        var artistLinks: String = ""
            private set
        var difficulty: Long = 1
            private set
        var requiredMods: List<String> = listOf()
            private set
        var songFilename: String = ""
            private set
        var bpm: Double = 100.0
            private set
        var volume: Long = 0
            private set
        var offset: Long = 0
            private set
        var pitch: Long = 100
            private set
        var hitsound: Hitsound = Hitsound.KICK
            private set
        var hitsoundVolume: Long = 100
            private set
        var countdownTicks: Long = 4
            private set
        var trackColorType: TrackColorType = TrackColorType.SINGLE
            private set
        var trackColor: AlphaColor = AlphaColor.Builder().rgba(0x00, 0x3f, 0x52, null).build()
            private set
        var secondaryTrackColor: AlphaColor = AlphaColor.Builder().rgba(0xff, 0xff, 0xff, null).build()
            private set
        var trackColorAnimDuration: Double = 2.0
            private set
        var trackColorPulse: TrackColorPulse = TrackColorPulse.NONE
            private set
        var trackPulseLength: Long = 10
            private set
        var trackStyle: TrackStyle = TrackStyle.STANDARD
            private set
        var trackTexture: String = ""
            private set
        var trackTextureScale: Double = 1.0
            private set
        var trackGlowIntensity: Double = 100.0
            private set
        var trackAnimation: TrackAnimation = TrackAnimation.NONE
            private set
        var beatsAhead: Long = 3
            private set
        var trackDisappearAnimation: TrackDisappearAnimation = TrackDisappearAnimation.NONE
            private set
        var beatsBehind: Long = 4
            private set
        var backgroundColor: Color = Color.Builder().rgb(0x00, 0x00, 0x00).build()
            private set
        var showDefaultBGIfNoImage: Toggle = Toggle.ENABLED
            private set
        var showDefaultBGTile: Toggle = Toggle.ENABLED
            private set
        var defaultBGTileColor: AlphaColor = AlphaColor.Builder().rgba(0x10, 0x11, 0x21, null).build()
            private set
        var defaultBGShapeType: DefaultBGShapeType = DefaultBGShapeType.DEFAULT
            private set
        var defaultBGShapeColor: AlphaColor = AlphaColor.Builder().rgba(0xff, 0xff, 0xff, null).build()
            private set
        var bgImage: String = ""
            private set
        var bgImageColor: Color = Color.Builder().rgb(0xff, 0xff, 0xff).build()
            private set
        var parallax: Pair<Double, Double> = Pair(100.0, 100.0)
            private set
        var bgDisplayMode: BGDisplayModeType = BGDisplayModeType.FIT_TO_SCREEN
            private set
        var imageSmoothing: Toggle = Toggle.ENABLED
            private set
        var lockRot: Toggle = Toggle.DISABLED
            private set
        var loopBG: Toggle = Toggle.DISABLED
            private set
        var scalingRatio: Long = 100
            private set
        var relativeTo: CameraRelativeTo = CameraRelativeTo.PLAYER
            private set
        var position: Pair<Double, Double> = Pair(100.0, 100.0)
            private set
        var rotation: Double = 0.0
            private set
        var zoom: Long = 0
            private set
        var pulseOnFloor: Toggle = Toggle.ENABLED
            private set
        var startCamLowVFX: Toggle = Toggle.DISABLED
            private set
        var bgVideo: String = ""
            private set
        var loopVideo: Toggle = Toggle.DISABLED
            private set
        var vidOffset: Long = 0
            private set
        var floorIconOutlines: Toggle = Toggle.DISABLED
            private set
        var stickToFloors: Toggle = Toggle.ENABLED
            private set
        var planetEase: Ease = Ease.LINEAR
            private set
        var planetEaseParts: Long = 1
            private set
        var planetEasePartBehavior: EasePartBehavior = EasePartBehavior.MIRROR
            private set
        var customClass: String = ""
            private set
        var defaultTextColor: AlphaColor = AlphaColor.Builder().rgba(0xff, 0xff, 0xff, null).build()
            private set
        var defaultTextShadowColor: AlphaColor = AlphaColor.Builder().rgba(0x00, 0x00, 0x00, 0x50).build()
            private set
        var congratsText: String = ""
            private set
        var perfectText: String = ""
            private set
        var legacyFlash: Boolean = false
            private set
        var legacyCamRelativeTo: Boolean = false
            private set
        var legacySpriteTiles: Boolean = false
            private set
        var unknownProperties: Map<String, JsonNode> = mapOf()
            private set

        fun version(version: Long) = apply { this.version = version.requireMin(1) }
        fun artist(artist: String) = apply { this.artist = artist }
        fun specialArtistType(specialArtistType: SpecialArtistType) =
            apply { this.specialArtistType = specialArtistType }

        fun artistPermission(artistPermission: String) = apply { this.artistPermission = artistPermission }
        fun song(song: String) = apply { this.song = song }
        fun author(author: String) = apply { this.author = author }
        fun separateCountdownTime(separateCountdownTime: Toggle) =
            apply { this.separateCountdownTime = separateCountdownTime }

        fun previewImage(previewImage: String) = apply { this.previewImage = previewImage }
        fun previewIcon(previewIcon: String) = apply { this.previewIcon = previewIcon }
        fun previewIconColor(previewIconColor: Color) = apply { this.previewIconColor = previewIconColor }
        fun previewSongStart(previewSongStart: Long) = apply { this.previewSongStart = previewSongStart }
        fun previewSongDuration(previewSongDuration: Long) = apply { this.previewSongDuration = previewSongDuration }
        fun seizureWarning(seizureWarning: Toggle) = apply { this.seizureWarning = seizureWarning }
        fun levelDesc(levelDesc: String) = apply { this.levelDesc = levelDesc }
        fun levelTags(levelTags: String) = apply { this.levelTags = levelTags }
        fun artistLinks(artistLinks: String) = apply { this.artistLinks = artistLinks }
        fun difficulty(difficulty: Long) = apply { this.difficulty = difficulty }
        fun requiredMods(requiredMods: List<String>) = apply { this.requiredMods = requiredMods.toList() }
        fun songFilename(songFilename: String) = apply { this.songFilename = songFilename }
        fun bpm(bpm: Double) = apply { this.bpm = bpm }
        fun volume(volume: Long) = apply { this.volume = volume }
        fun offset(offset: Long) = apply { this.offset = offset }
        fun pitch(pitch: Long) = apply { this.pitch = pitch }
        fun hitsound(hitsound: Hitsound) = apply { this.hitsound = hitsound }
        fun hitsoundVolume(hitsoundVolume: Long) = apply { this.hitsoundVolume = hitsoundVolume }
        fun countdownTicks(countdownTicks: Long) = apply { this.countdownTicks = countdownTicks }
        fun trackColorType(trackColorType: TrackColorType) = apply { this.trackColorType = trackColorType }
        fun trackColor(trackColor: AlphaColor) = apply { this.trackColor = trackColor }
        fun secondaryTrackColor(secondaryTrackColor: AlphaColor) =
            apply { this.secondaryTrackColor = secondaryTrackColor }

        fun trackColorAnimDuration(trackColorAnimDuration: Double) =
            apply { this.trackColorAnimDuration = trackColorAnimDuration }

        fun trackColorPulse(trackColorPulse: TrackColorPulse) = apply { this.trackColorPulse = trackColorPulse }
        fun trackPulseLength(trackPulseLength: Long) = apply { this.trackPulseLength = trackPulseLength }
        fun trackStyle(trackStyle: TrackStyle) = apply { this.trackStyle = trackStyle }
        fun trackTexture(trackTexture: String) = apply { this.trackTexture = trackTexture }
        fun trackTextureScale(trackTextureScale: Double) = apply { this.trackTextureScale = trackTextureScale }
        fun trackGlowIntensity(trackGlowIntensity: Double) = apply { this.trackGlowIntensity = trackGlowIntensity }
        fun trackAnimation(trackAnimation: TrackAnimation) = apply { this.trackAnimation = trackAnimation }
        fun beatsAhead(beatsAhead: Long) = apply { this.beatsAhead = beatsAhead }
        fun trackDisappearAnimation(trackDisappearAnimation: TrackDisappearAnimation) =
            apply { this.trackDisappearAnimation = trackDisappearAnimation }

        fun beatsBehind(beatsBehind: Long) = apply { this.beatsBehind = beatsBehind }
        fun backgroundColor(backgroundColor: Color) = apply { this.backgroundColor = backgroundColor }
        fun showDefaultBGIfNoImage(showDefaultBGIfNoImage: Toggle) =
            apply { this.showDefaultBGIfNoImage = showDefaultBGIfNoImage }

        fun showDefaultBGTile(showDefaultBGTile: Toggle) = apply { this.showDefaultBGTile = showDefaultBGTile }
        fun defaultBGTileColor(defaultBGTileColor: AlphaColor) = apply { this.defaultBGTileColor = defaultBGTileColor }
        fun defaultBGShapeType(defaultBGShapeType: DefaultBGShapeType) =
            apply { this.defaultBGShapeType = defaultBGShapeType }

        fun defaultBGShapeColor(defaultBGShapeColor: AlphaColor) =
            apply { this.defaultBGShapeColor = defaultBGShapeColor }

        fun bgImage(bgImage: String) = apply { this.bgImage = bgImage }
        fun bgImageColor(bgImageColor: Color) = apply { this.bgImageColor = bgImageColor }
        fun parallax(parallax: Pair<Double, Double>) = apply { this.parallax = parallax }
        fun bgDisplayMode(bgDisplayMode: BGDisplayModeType) = apply { this.bgDisplayMode = bgDisplayMode }
        fun imageSmoothing(imageSmoothing: Toggle) = apply { this.imageSmoothing = imageSmoothing }
        fun lockRot(lockRot: Toggle) = apply { this.lockRot = lockRot }
        fun loopBG(loopBG: Toggle) = apply { this.loopBG = loopBG }
        fun scalingRatio(scalingRatio: Long) = apply { this.scalingRatio = scalingRatio }
        fun relativeTo(relativeTo: CameraRelativeTo) = apply { this.relativeTo = relativeTo }
        fun position(position: Pair<Double, Double>) = apply { this.position = position }
        fun rotation(rotation: Double) = apply { this.rotation = rotation }
        fun zoom(zoom: Long) = apply { this.zoom = zoom }
        fun pulseOnFloor(pulseOnFloor: Toggle) = apply { this.pulseOnFloor = pulseOnFloor }
        fun startCamLowVFX(startCamLowVFX: Toggle) = apply { this.startCamLowVFX = startCamLowVFX }
        fun bgVideo(bgVideo: String) = apply { this.bgVideo = bgVideo }
        fun loopVideo(loopVideo: Toggle) = apply { this.loopVideo = loopVideo }
        fun vidOffset(vidOffset: Long) = apply { this.vidOffset = vidOffset }
        fun floorIconOutlines(floorIconOutlines: Toggle) = apply { this.floorIconOutlines = floorIconOutlines }
        fun stickToFloors(stickToFloors: Toggle) = apply { this.stickToFloors = stickToFloors }
        fun planetEase(planetEase: Ease) = apply { this.planetEase = planetEase }
        fun planetEaseParts(planetEaseParts: Long) = apply { this.planetEaseParts = planetEaseParts }
        fun planetEasePartBehavior(planetEasePartBehavior: EasePartBehavior) = apply { this.planetEasePartBehavior = planetEasePartBehavior }
        fun customClass(customClass: String) = apply { this.customClass = customClass }
        fun defaultTextColor(defaultTextColor: AlphaColor) = apply { this.defaultTextColor = defaultTextColor }
        fun defaultTextShadowColor(defaultTextShadowColor: AlphaColor) =
            apply { this.defaultTextShadowColor = defaultTextShadowColor }

        fun congratsText(congratsText: String) = apply { this.congratsText = congratsText }
        fun perfectText(perfectText: String) = apply { this.perfectText = perfectText }
        fun legacyFlash(legacyFlash: Boolean) = apply { this.legacyFlash = legacyFlash }
        fun legacyCamRelativeTo(legacyCamRelativeTo: Boolean) = apply { this.legacyCamRelativeTo = legacyCamRelativeTo }
        fun legacySpriteTiles(legacySpriteTiles: Boolean) = apply { this.legacySpriteTiles = legacySpriteTiles }
        fun unknownProperties(unknownProperties: Map<String, JsonNode>) =
            apply { this.unknownProperties = unknownProperties.toMap() }

        fun build() = CustomLevelSetting(
            version,
            artist,
            specialArtistType,
            artistPermission,
            song,
            author,
            separateCountdownTime,
            previewImage,
            previewIcon,
            previewIconColor,
            previewSongStart,
            previewSongDuration,
            seizureWarning,
            levelDesc,
            levelTags,
            artistLinks,
            difficulty,
            requiredMods,
            songFilename,
            bpm,
            volume,
            offset,
            pitch,
            hitsound,
            hitsoundVolume,
            countdownTicks,
            trackColorType,
            trackColor,
            secondaryTrackColor,
            trackColorAnimDuration,
            trackColorPulse,
            trackPulseLength,
            trackStyle,
            trackTexture,
            trackTextureScale,
            trackGlowIntensity,
            trackAnimation,
            beatsAhead,
            trackDisappearAnimation,
            beatsBehind,
            backgroundColor,
            showDefaultBGIfNoImage,
            showDefaultBGTile,
            defaultBGTileColor,
            defaultBGShapeType,
            defaultBGShapeColor,
            bgImage,
            bgImageColor,
            parallax,
            bgDisplayMode,
            imageSmoothing,
            lockRot,
            loopBG,
            scalingRatio,
            relativeTo,
            position,
            rotation,
            zoom,
            pulseOnFloor,
            startCamLowVFX,
            bgVideo,
            loopVideo,
            vidOffset,
            floorIconOutlines,
            stickToFloors,
            planetEase,
            planetEaseParts,
            planetEasePartBehavior,
            customClass,
            defaultTextColor,
            defaultTextShadowColor,
            congratsText,
            perfectText,
            legacyFlash,
            legacyCamRelativeTo,
            legacySpriteTiles,
            unknownProperties,
        )
    }
}
