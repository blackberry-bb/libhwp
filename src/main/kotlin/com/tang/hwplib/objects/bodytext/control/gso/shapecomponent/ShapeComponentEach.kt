package com.tang.hwplib.objects.bodytext.control.gso.shapecomponent

import com.tang.hwplib.annotation.ID
import com.tang.hwplib.annotation.IDTypes
import com.tang.hwplib.annotation.LinkID
import com.tang.hwplib.objects.bodytext.control.gso.shapecomponent.arc.HWPArcBorder
import com.tang.hwplib.objects.bodytext.control.gso.shapecomponent.curve.HWPCurveSegmentType
import com.tang.hwplib.objects.bodytext.control.gso.shapecomponent.ellipse.HWPShapeComponentEllipseProperty
import com.tang.hwplib.objects.bodytext.control.gso.shapecomponent.line.HWPLineInfoProperty
import com.tang.hwplib.objects.bodytext.control.gso.shapecomponent.ole.HWPShapeComponentOLEProperty
import com.tang.hwplib.objects.bodytext.control.gso.shapecomponent.picture.HWPInnerMargin
import com.tang.hwplib.objects.bodytext.control.gso.shapecomponent.picture.HWPPictureEffect
import com.tang.hwplib.objects.bodytext.control.gso.shapecomponent.polygon.HWPPositionXY
import com.tang.hwplib.objects.bodytext.control.gso.video.HWPVideoProperty
import com.tang.hwplib.objects.bodytext.control.gso.video.HWPVideoType
import com.tang.hwplib.objects.docinfo.HWPBinData
import com.tang.hwplib.objects.docinfo.borderfill.fillinfo.HWPPictureInfo
import com.tang.hwplib.objects.etc.Color4Byte

/**
 * 호 개체 속성을 나타내는 객체
 * 28 bytes
 *
 * @author accforaus
 *
 * @property [arcBorder] [HWPArcBorder], 속성 (UINT32 - unsigned 4 bytes)
 * @property [centerX] [Int], 타원의 중심 좌표 X 값 (INT32 - signed 4 bytes)
 * @property [centerY] [Int], 타원의 중심 좌표 Y 값 (INT32 - signed 4 bytes)
 * @property [axis1X] [Int], 제 1축 X 좌표 값 (INT32 - signed 4 bytes)
 * @property [axis1Y] [Int], 제 1축 Y 좌표 값 (INT32 - signed 4 bytes)
 * @property [axis2X] [Int], 제 2축 X 좌표 값 (INT32 - signed 4 bytes)
 * @property [axis2Y] [Int], 제 2축 Y 좌표 값 (INT32 - signed 4 bytes)
 */
class HWPShapeComponentArc {
    var arcBorder: HWPArcBorder = HWPArcBorder.Arc
    var centerX: Int = 0
    var centerY: Int = 0
    var axis1X: Int = 0
    var axis1Y: Int = 0
    var axis2X: Int = 0
    var axis2Y: Int = 0

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPShapeComponentArc] 복사된 객체 반환
     */
    fun copy() : HWPShapeComponentArc = HWPShapeComponentArc().also {
        it.arcBorder = HWPArcBorder.valueOf(this.arcBorder.value)
        it.centerX = this.centerX
        it.centerY = this.centerY
        it.axis1X = this.axis1X
        it.axis1Y = this.axis1Y
        it.axis2X = this.axis2X
        it.axis2Y = this.axis2Y
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPShapeComponentArc] 생성된 객체 반환
         */
        fun build(arcBorder: HWPArcBorder = HWPArcBorder.Arc,
                  centerX: Int = 0, centerY: Int = 0,
                  axis1X: Int = 0, axis1Y: Int = 0,
                  axis2X: Int = 0, axis2Y: Int = 0)
                : HWPShapeComponentArc = HWPShapeComponentArc().apply {
            this.arcBorder = arcBorder
            this.centerX = centerX
            this.centerY = centerY
            this.axis1X = axis1X
            this.axis1Y = axis1Y
            this.axis2X = axis2X
            this.axis2Y = axis2Y
        }
    }
}

/**
 * 곡선 개체 속성을 나타내는 객체
 * 가변 길이
 *
 * @author accforaus
 *
 * @property [positionList] [ArrayList], x/y 좌표 값의 리스트
 * @property [segmentTypeList] [ArrayList], segment type 리스트
 */
class HWPShapeComponentCurve {
    var positionList: ArrayList<HWPPositionXY> = ArrayList()
    var segmentTypeList: ArrayList<HWPCurveSegmentType> = ArrayList()

    /**
     * X/Y 좌표를 추가하고 반환하는 함수
     *
     * @return [HWPPositionXY] 생성된 객체 반환
     */
    fun addPosition() : HWPPositionXY = HWPPositionXY().apply { positionList.add(this) }

    /**
     * 새로운 segment type을 추가하는 함수
     *
     * @param [cst] [HWPCurveSegmentType] 추가할 segment type
     */
    fun addCurveSegmentType(cst: HWPCurveSegmentType) {
        segmentTypeList.add(cst)
    }

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPShapeComponentCurve] 복사된 객체 반환
     */
    fun copy() : HWPShapeComponentCurve = HWPShapeComponentCurve().also {
        for (position in this.positionList) it.positionList.add(position.copy())
        for (segmentType in this.segmentTypeList) it.segmentTypeList.add(HWPCurveSegmentType.valueOf(segmentType.value))
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPShapeComponentCurve] 생성된 객체 반환
         */
        fun build(
                positionGenerator: () -> ArrayList<HWPPositionXY> = {ArrayList()},
                segmentTypeGenerator: () -> ArrayList<HWPCurveSegmentType> = {ArrayList()}
        )
                : HWPShapeComponentCurve = HWPShapeComponentCurve().apply {
            this.positionList = positionGenerator()
            this.segmentTypeList = segmentTypeGenerator()
        }
    }
}

/**
 * 타원 개체 속성을 나타내는 객체
 * 33 bytes
 *
 * @author accforaus
 *
 * @property [property] [HWPShapeComponentEllipseProperty], 속성 (UINT32 - unsigned 4 bytes)
 * @property [centerX] [Int], 중심 좌표의 X 값 (INT32 - signed 4 bytes)
 * @property [centerY] [Int], 중심 좌표의 Y 값 (INT32 - signed 4 bytes)
 * @property [axis1X] [Int], 제1축 X 좌표 값 (INT32 - signed 4 bytes)
 * @property [axis1Y] [Int], 제1축 Y 좌표 값 (INT32 - signed 4 bytes)
 * @property [axis2X] [Int], 제2축 X 좌표 값 (INT32 - signed 4 bytes)
 * @property [axis2Y] [Int], 제2축 Y 좌표 값 (INT32 - signed 4 bytes)
 * @property [startX] [Int], start pos X (INT32 - signed 4 bytes)
 * @property [startY] [Int], start pos Y (INT32 - signed 4 bytes)
 * @property [endX] [Int], end pos X (INT32 - signed 4 bytes)
 * @property [endY] [Int], end pos Y (INT32 - signed 4 bytes)
 * @property [startX2] [Int], start pos X2 (INT32 - signed 4 bytes)
 * @property [startY2] [Int], start pos Y2 (INT32 - signed 4 bytes)
 * @property [endX2] [Int], end pos X2 (INT32 - signed 4 bytes)
 * @property [endY2] [Int], end pos Y2 (INT32 - signed 4 bytes)
 */
class HWPShapeComponentEllipse {
    var property: HWPShapeComponentEllipseProperty = HWPShapeComponentEllipseProperty()
    var centerX: Int = 0
    var centerY: Int = 0
    var axis1X: Int = 0
    var axis1Y: Int = 0
    var axis2X: Int = 0
    var axis2Y: Int = 0
    var startX: Int = 0
    var startY: Int = 0
    var endX: Int = 0
    var endY: Int = 0
    var startX2: Int = 0
    var startY2: Int = 0
    var endX2: Int = 0
    var endY2: Int = 0

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPShapeComponentEllipse] 복사된 객체 반환
     */
    fun copy() : HWPShapeComponentEllipse = HWPShapeComponentEllipse().also {
        it.property.value = this.property.value
        it.centerX = this.centerX
        it.centerY = this.centerY
        it.axis1X = this.axis1X
        it.axis1Y = this.axis1Y
        it.axis2X = this.axis2X
        it.axis2Y = this.axis2Y
        it.startX = this.startX
        it.startY = this.startY
        it.endX = this.endX
        it.endY = this.endY
        it.startX2 = this.startX2
        it.startY2 = this.startY2
        it.endX2 = this.endX2
        it.endY2 = this.endY2
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPShapeComponentEllipse] 생성된 객체 반환
         */
        fun build(property: HWPShapeComponentEllipseProperty = HWPShapeComponentEllipseProperty.build(),
                  centerX: Int = 0, centerY: Int = 0, axis1X: Int = 0, axis1Y: Int = 0,
                  axis2X: Int = 0, axis2Y: Int = 0, startX: Int = 0, startY: Int = 0,
                  endX: Int = 0, endY: Int = 0, startX2: Int = 0, startY2: Int = 0,
                  endX2: Int = 0, endY2: Int = 0)
                : HWPShapeComponentEllipse = HWPShapeComponentEllipse().apply {
            this.property = property
            this.centerX = centerX
            this.centerY = centerY
            this.axis1X = axis1X
            this.axis1Y = axis1Y
            this.axis2X = axis2X
            this.axis2Y = axis2Y
            this.startX = startX
            this.startY = startY
            this.endX = endX
            this.endY = endY
            this.startX2 = startX2
            this.startY2 = startY2
            this.endX2 = endX2
            this.endY2 = endY2
        }
    }
}

/**
 * 선 개체 속성을 나타내는 객체
 * BYTE stream - unsigned 18 bytes
 *
 * @author accforaus
 *
 * @property [startX] [Int], 시작점 X 좌표 (INT32 - signed 4 bytes)
 * @property [startY] [Int], 시작점 Y 좌표 (INT32 - signed 4 bytes)
 * @property [endX] [Int], 끝점 X 좌표 (INT32 - signed 4 bytes)
 * @property [endY] [Int], 끝점 Y 좌표 (INT32 - signed 4 bytes)
 * @property [startedRightOrBottom] [Boolean], 속성. 처음 생성 시 수직 또는 수평선일 때, 선의 방향이 언제나 오른쪽 (위쪽)으로 잡힘으로 인한 현상 때문에 방향을 바르게 잡아주기 위한 플래그 (UINT16 - unsigned 2 bytes)
 */
class HWPShapeComponentLine {
    var startX: Int = 0
    var startY: Int = 0
    var endX: Int = 0
    var endY: Int = 0
    var startedRightOrBottom: Boolean = false

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPShapeComponentLine] 복사된 객체 반환
     */
    fun copy() : HWPShapeComponentLine = HWPShapeComponentLine().also {
        it.startX = this.startX
        it.startY = this.startY
        it.endX = this.endX
        it.endY = this.endY
        it.startedRightOrBottom = this.startedRightOrBottom
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPShapeComponentLine] 생성된 객체 반환
         */
        fun build(startX: Int = 0, startY: Int = 0,
                  endX: Int = 0, endY: Int = 0,
                  startedRightOrBottom: Boolean = false)
                : HWPShapeComponentLine = HWPShapeComponentLine().apply {
            this.startX = startX
            this.startY = startY
            this.endX = endX
            this.endY = endY
            this.startedRightOrBottom = startedRightOrBottom
        }
    }
}

/**
 * 연결 선 개체 속성을 나타내는 객체
 * BYTE stream
 *
 * @author accforaus
 *
 * @property [startX] [Int], 시작점 X 좌표 (INT32 - signed 4 bytes)
 * @property [startY] [Int], 시작점 Y 좌표 (INT32 - signed 4 bytes)
 * @property [endX] [Int], 끝점 X 좌표 (INT32 - signed 4 bytes)
 * @property [endY] [Int], 끝점 Y 좌표 (INT32 - signed 4 bytes)
 * @property [unknown] [ByteArray]
 */
class HWPShapeComponentLineForObjectLinkLine {
    var startX: Int = 0
    var startY: Int = 0
    var endX: Int = 0
    var endY: Int = 0
    var unknown: ByteArray? = null

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPShapeComponentLineForObjectLinkLine] 복사된 객체 반환
     */
    fun copy() : HWPShapeComponentLineForObjectLinkLine = HWPShapeComponentLineForObjectLinkLine().also {
        it.startX = this.startX
        it.startY = this.startY
        it.endX = this.endX
        it.endY = this.endY
        it.unknown = this.unknown?.copyOf()
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPShapeComponentLineForObjectLinkLine] 생성된 객체 반환
         */
        fun build(startX: Int = 0, startY: Int = 0,
                  endX: Int = 0, endY: Int = 0, unknown: ByteArray? = null)
                : HWPShapeComponentLineForObjectLinkLine = HWPShapeComponentLineForObjectLinkLine().apply {
            this.startX = startX
            this.startY = startY
            this.endX = endX
            this.endY = endY
            this.unknown = unknown
        }
    }
}

/**
 * OLE 개체 속성을 나타내는 객체
 * 24 bytes
 *
 * @author accforaus
 *
 * @property [property] [HWPShapeComponentOLEProperty], 속성 (UINT16 - unsigned 2 bytes)
 * @property [extentWidth] [Int], 오브젝트 자체의 extent x크기 (INT32 - signed 4 bytes)
 * @property [extentHeight] [Int], 오브젝트 자체의 extent y크기 (INT32 - signed 4 bytes)
 * @property [binDataId] [Int], 오브젝트가 사용하는 스토리지의 HWPBinData[HWPBinData] ID (UINT16 - unsigned 2 bytes)
 * @property [borderColor] [Color4Byte], 테두리 색 (COLORREF - unsigned 4 bytes)
 * @property [borderThickness] [Int], 테두리 두께 (INT32 - signed 4 bytes)
 * @property [borderProperty] [HWPLineInfoProperty], 테두리 속성 (UINT32 - unsigned 4 bytes)
 */
@LinkID class HWPShapeComponentOLE {
    var property: HWPShapeComponentOLEProperty = HWPShapeComponentOLEProperty()
    var extentWidth: Int = 0
    var extentHeight: Int = 0
    @ID(IDTypes.BinData)
    var binDataId: Int = 0
    var borderColor: Color4Byte = Color4Byte()
    var borderThickness: Int = 0
    var borderProperty: HWPLineInfoProperty = HWPLineInfoProperty()

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPShapeComponentOLE] 복사된 객체 반환
     */
    fun copy() : HWPShapeComponentOLE = HWPShapeComponentOLE().also {
        it.property.value = this.property.value
        it.extentWidth = this.extentWidth
        it.extentHeight = this.extentHeight
        it.binDataId = this.binDataId
        it.borderColor.value = this.borderColor.value
        it.borderThickness = this.borderThickness
        it.borderProperty.value = this.borderProperty.value
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPShapeComponentOLE] 생성된 객체 반환
         */
        fun build(property: HWPShapeComponentOLEProperty = HWPShapeComponentOLEProperty.build(),
                  extentWidth: Int = 0, extentHeight: Int = 0,
                  binDataId: Int = 0, borderColor: Color4Byte = Color4Byte.build(),
                  borderThickness: Int = 0, borderProperty: HWPLineInfoProperty = HWPLineInfoProperty.build())
                : HWPShapeComponentOLE = HWPShapeComponentOLE().apply {
            this.property = property
            this.extentWidth = extentWidth
            this.extentHeight = extentHeight
            this.binDataId = binDataId
            this.borderColor = borderColor
            this.borderThickness = borderThickness
            this.borderProperty = borderProperty
        }
    }
}

/**
 * 그림 개체 속성을 나타내는 객체
 * 가변 길이
 *
 * @author accforaus
 *
 * @property [borderColor] [Color4Byte], 테두리 색 (COLORREF - unsigned 4 bytes)
 * @property [borderThickness] [Int], 테두리 두께 (INT32 - signed 4 bytes)
 * @property [borderProperty] [HWPLineInfoProperty], 테두리 속성 (UINT32 - unsigned 4 bytes)
 * @property [leftTop] [HWPPositionXY], 이미지 테두리 사각형의 왼쪽 위 (x, y)좌표 (INT32 - signed 4 bytes)
 * @property [rightTop] [HWPPositionXY], 이미지 테두리 사각형의 오른쪽 위 (x, y)좌표 (INT32 - signed 4 bytes)
 * @property [leftBottom] [HWPPositionXY], 이미지 테두리 사각형의 왼쪽 아래 (x, y)좌표 (INT32 - signed 4 bytes)
 * @property [rightBottom] [HWPPositionXY], 이미지 테두리 사각형의 오른쪽 위 (x, y)좌표 (INT32 - signed 4 bytes)
 * @property [leftAfterCutting] [Int], 자르기 한 후 사각형의 left (INT32 - signed 4 bytes)
 * @property [topAfterCutting] [Int], 자르기 한 후 사각형의 top (INT32 - signed 4 bytes)
 * @property [rightAfterCutting] [Int], 자르기 한 후 사각형의 right (INT32 - signed 4 bytes)
 * @property [bottomAfterCutting] [Int], 자르기 한 후 사각형의 bottom (INT32 - signed 4 bytes)
 * @property [innerMargin] [HWPInnerMargin], 안쪽 여백 정보 (BYTE stream - unsigned 8 bytes)
 * @property [pictureInfo] [HWPPictureInfo], 그림 정보 (BYTE stream)
 * @property [borderTransparency] [Short], 테두리 투명도 (BYTE - unsigned 1 byte)
 * @property [instanceId] [Long], 문서 내 각 개체에 대한 고유 아이디(instance ID) (UINT32 - unsigned 4 bytes)
 * @property [pictureEffect] [HWPPictureEffect], 그림 효과 정보 (BYTE stream)
 * @property [imageWidth] [Long], 그림 최초 생성 시 기준 이미지 폭 (HWPUNIT - unsigned 4 bytes)
 * @property [imageHeight] [Long], 그림 최초 생성 시 기준 이미지 높이 (HWPUNIT - unsigned 4 bytes)
 * @property [imageTransparency] [Byte], 이미지 투명도
 */
class HWPShapeComponentPicture {
    var borderColor: Color4Byte = Color4Byte()
    var borderThickness: Int = 0
    var borderProperty: HWPLineInfoProperty = HWPLineInfoProperty()
    var leftTop: HWPPositionXY = HWPPositionXY()
    var rightTop: HWPPositionXY = HWPPositionXY()
    var leftBottom: HWPPositionXY = HWPPositionXY()
    var rightBottom: HWPPositionXY = HWPPositionXY()
    var leftAfterCutting: Int = 0
    var topAfterCutting: Int = 0
    var rightAfterCutting: Int = 0
    var bottomAfterCutting: Int = 0
    var innerMargin: HWPInnerMargin = HWPInnerMargin()
    var pictureInfo: HWPPictureInfo = HWPPictureInfo()
    var borderTransparency: Short = 0
    var instanceId:Long = 0
    var pictureEffect: HWPPictureEffect = HWPPictureEffect()
    var imageWidth: Long = 0
    var imageHeight: Long = 0
    var imageTransparency: Byte = 0

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPShapeComponentPicture] 복사된 객체 반환
     */
    fun copy() : HWPShapeComponentPicture = HWPShapeComponentPicture().also {
        it.borderColor.value = this.borderColor.value
        it.borderThickness = this.borderThickness
        it.borderProperty.value = this.borderProperty.value
        it.leftTop = this.leftTop.copy()
        it.rightTop = this.rightTop.copy()
        it.leftBottom = this.leftBottom.copy()
        it.rightBottom = this.rightBottom.copy()
        it.leftAfterCutting = this.leftAfterCutting
        it.topAfterCutting = this.topAfterCutting
        it.rightAfterCutting = this.rightAfterCutting
        it.bottomAfterCutting = this.bottomAfterCutting
        it.innerMargin = this.innerMargin.copy()
        it.pictureInfo = this.pictureInfo.copy()
        it.borderTransparency = this.borderTransparency
        it.instanceId = this.instanceId
        it.pictureEffect = this.pictureEffect.copy()
        it.imageWidth = this.imageWidth
        it.imageHeight = this.imageHeight
        it.imageTransparency = this.imageTransparency
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPShapeComponentPicture] 생성된 객체 반환
         */
        fun build(borderColor: Color4Byte = Color4Byte.build(),
                  borderThickness: Int = 0, borderProperty: HWPLineInfoProperty = HWPLineInfoProperty.build(),
                  leftTop: HWPPositionXY = HWPPositionXY.build(),
                  rightTop: HWPPositionXY = HWPPositionXY.build(),
                  leftBottom: HWPPositionXY = HWPPositionXY.build(),
                  rightBottom: HWPPositionXY = HWPPositionXY.build(),
                  leftAfterCutting: Int = 0, topAfterCutting: Int = 0, rightAfterCutting: Int = 0,
                  bottomAfterCutting: Int = 0, innerMargin: HWPInnerMargin = HWPInnerMargin.build(),
                  pictureInfo: HWPPictureInfo = HWPPictureInfo.build(),
                  borderTransparency: Short = 0, pictureEffect: HWPPictureEffect = HWPPictureEffect.build(),
                  imageWidth: Long = 0, imageHeight: Long = 0, imageTransparency: Byte = 0)
                : HWPShapeComponentPicture = HWPShapeComponentPicture().apply {
            this.borderColor = borderColor
            this.borderThickness = borderThickness
            this.borderProperty = borderProperty
            this.leftTop = leftTop
            this.rightTop = rightTop
            this.leftBottom = leftBottom
            this.rightBottom = rightBottom
            this.leftAfterCutting = leftAfterCutting
            this.topAfterCutting = topAfterCutting
            this.rightAfterCutting = rightAfterCutting
            this.bottomAfterCutting = bottomAfterCutting
            this.innerMargin = innerMargin
            this.pictureInfo = pictureInfo
            this.borderTransparency = borderTransparency
            this.pictureEffect = pictureEffect
            this.imageWidth = imageWidth
            this.imageHeight = imageHeight
            this.imageTransparency = imageTransparency
        }
    }
}

/**
 * 다각형 개체 속성을 나타내는 객체
 * 가변 길이
 *
 * @author accforaus
 *
 * @property [positionList] [ArrayList], x좌표와 y좌표를 가진 리스트 (INT32 array)
 */
class HWPShapeComponentPolygon {
    var positionList: ArrayList<HWPPositionXY> = ArrayList()

    /**
     * X/Y좌표를 추가하고 반환하는 함수
     *
     * @return [HWPPositionXY] 생성된 객체 반환
     */
    fun addNewPosition() : HWPPositionXY = HWPPositionXY().apply { positionList.add(this) }

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPShapeComponentPolygon] 복사된 객체 반환
     */
    fun copy() : HWPShapeComponentPolygon = HWPShapeComponentPolygon().also {
        for (position in this.positionList) it.positionList.add(position.copy())
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPShapeComponentPolygon] 생성된 객체 반환
         */
        fun build(
                positionGenerator: () -> ArrayList<HWPPositionXY> = {ArrayList()}
        ) : HWPShapeComponentPolygon = HWPShapeComponentPolygon().apply {
            this.positionList = positionGenerator()
        }
    }
}

/**
 * 사각형 개체 속성을 나타내는 객체
 * 33 bytes
 * @author accforaus
 *
 * @property [roundRate] [Byte], 사각형 모서리 곡률(%) 직각은 0, 둥근 모양은 20, 반원은 50, 그 외는 적당한 값을 % 단위로 사용한다.
 * @property [x1] [Int], 사각형의 좌표 (x1) (INT32 - signed 4 bytes)
 * @property [y1] [Int], 사각형의 좌표 (y1) (INT32 - signed 4 bytes)
 * @property [x2] [Int], 사각형의 좌표 (x2) (INT32 - signed 4 bytes)
 * @property [y2] [Int], 사각형의 좌표 (y2) (INT32 - signed 4 bytes)
 * @property [x3] [Int], 사각형의 좌표 (x3) (INT32 - signed 4 bytes)
 * @property [y3] [Int], 사각형의 좌표 (y3) (INT32 - signed 4 bytes)
 * @property [x4] [Int], 사각형의 좌표 (x4) (INT32 - signed 4 bytes)
 * @property [y4] [Int], 사각형의 좌표 (y4) (INT32 - signed 4 bytes)
 */
class HWPShapeComponentRectangle {
    var roundRate: Byte = 0
    var x1: Int = 0
    var y1: Int = 0
    var x2: Int = 0
    var y2: Int = 0
    var x3: Int = 0
    var y3: Int = 0
    var x4: Int = 0
    var y4: Int = 0

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPShapeComponentRectangle] 복사된 객체 반환
     */
    fun copy() : HWPShapeComponentRectangle = HWPShapeComponentRectangle().also {
        it.roundRate = this.roundRate
        it.x1 = this.x1
        it.y1 = this.y1
        it.x2 = this.x2
        it.y2 = this.y2
        it.x3 = this.x3
        it.y3 = this.y3
        it.x4 = this.x4
        it.y4 = this.y4
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPShapeComponentRectangle] 생성된 객체 반환
         */
        fun build(x1: Int = 0, y1: Int = 0, x2: Int = 0, y2: Int = 0,
                  x3: Int = 0, y3: Int = 0, x4: Int = 0, y4: Int = 0)
                : HWPShapeComponentRectangle = HWPShapeComponentRectangle().apply {
            this.x1 = x1
            this.y1 = y1
            this.x2 = x2
            this.y2 = y2
            this.x3 = x3
            this.y3 = y3
            this.x4 = x4
            this.y4 = y4
        }
    }
}

/**
 * 동영상 개체 속성을 나타내는 객체
 * 가변 길이
 * @author accforaus
 *
 * @property [videoType] [HWPVideoType], 동영상 타입 (INT32 - signed 4 bytes)
 * @property [videoProperty] [HWPVideoProperty], 동영상 타입에 따라 길이가 다름 (BYTE stream)
 */
class HWPShapeComponentVideo {
    var videoType: HWPVideoType = HWPVideoType.Local
    var videoProperty: HWPVideoProperty? = null

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPShapeComponentVideo] 복사된 객체 반환
     */
    fun copy() : HWPShapeComponentVideo = HWPShapeComponentVideo().also {
        it.videoType.value = this.videoType.value
        this.videoProperty?.run { it.videoProperty = this.copy() }
    }
}