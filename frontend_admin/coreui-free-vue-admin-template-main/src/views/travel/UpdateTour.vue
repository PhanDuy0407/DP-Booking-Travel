<template>
  <el-form label-width="160px">
    <CRow>
      <CCol>
        <el-form-item label="Mã code">
          <el-input v-model="tourDataDetail.code" />
        </el-form-item>
      </CCol>
      <CCol>
        <el-form-item label="Tên tour">
          <el-input v-model="tourDataDetail.name" />
        </el-form-item>
      </CCol>
    </CRow>
    <CRow>
      <CCol>
        <el-form-item label="Thời gian bắt đầu">
          <el-input v-model="tourDataDetail.startTime" type="date" />
        </el-form-item>
      </CCol>
      <CCol>
        <el-form-item label="Khoảng thời gian">
          <el-input v-model="tourDataDetail.period" />
        </el-form-item>
      </CCol>
    </CRow>
    <CRow>
      <CCol>
        <el-form-item label="Địa điểm bắt đầu">
          <el-select
            v-model="tourDataDetail.startPlaceId"
            class="m-2"
            placeholder="Chọn địa điểm bắt đầu"
          >
            <el-option
              v-for="item in placeListGet"
              :key="item.id"
              :label="item.name"
              :value="item.id"
            />
          </el-select>
        </el-form-item>
      </CCol>
      <CCol>
        <el-form-item label="Địa điểm kết thúc">
          <el-select
            v-model="tourDataDetail.endPlaceId"
            class="m-2"
            placeholder="Chọn địa điểm kết thúc"
          >
            <el-option
              v-for="item in placeListGet"
              :key="item.id"
              :label="item.name"
              :value="item.id"
            />
          </el-select>
        </el-form-item>
      </CCol>
    </CRow>
    <CRow>
      <CCol>
        <el-form-item label="Số chỗ">
          <el-input v-model="tourDataDetail.placeOrderMax" />
        </el-form-item>
      </CCol>
      <CCol>
        <el-form-item label="Trạng thái">
          <el-select
            v-model="tourDataDetail.status"
            class="m-2"
            placeholder="Chọn trạng thái"
          >
            <el-option
              v-for="item in statusList"
              :key="item.id"
              :label="item.value"
              :value="item.id"
            />
          </el-select>
        </el-form-item>
      </CCol>
    </CRow>
    <CRow>
      <CCol>
        <el-form-item label="URL ảnh">
          <el-input v-model="tourDataDetail.mainImageUrl" />
        </el-form-item>
      </CCol>
    </CRow>
    <CRow>
      <CCol>
        <el-form-item label="Mô tả">
          <el-input
            type="textarea"
            v-model="tourDataDetail.shortDesc"
            :rows="6"
          />
        </el-form-item>
      </CCol>
    </CRow>
    <CRow>
      <CCol>
        <el-collapse>
          <el-collapse-item title="Giá" name="1">
            <CRow>
              <CCol>
                <h6 style="text-align: center">Giá tour</h6>
                <el-form-item label="Giá người trưởng thành">
                  <el-input v-model="getTourPriceById.adult" />
                </el-form-item>
                <el-form-item label="Giá trẻ em 12-18 tuổi">
                  <el-input v-model="getTourPriceById.children" />
                </el-form-item>
                <el-form-item label="Giá trẻ em 2-12 tuổi">
                  <el-input v-model="getTourPriceById.kid" />
                </el-form-item>
                <el-form-item label="Giá trẻ em <2 tuổi">
                  <el-input v-model="getTourPriceById.baby" />
                </el-form-item>
                <el-form-item label="Phụ phí">
                  <el-input v-model="getTourPriceById.surcharge" />
                </el-form-item>
              </CCol>
              <CCol>
                <h6 style="text-align: center">Giá land tour</h6>
                <el-form-item label="Giá người trưởng thành">
                  <el-input v-model="getLandTourPriceById.adult" />
                </el-form-item>
                <el-form-item label="Giá trẻ em 12-18 tuổi">
                  <el-input v-model="getLandTourPriceById.children" />
                </el-form-item>
                <el-form-item label="Giá trẻ em 2-12 tuổi">
                  <el-input v-model="getLandTourPriceById.kid" />
                </el-form-item>
                <el-form-item label="Giá trẻ em <2 tuổi">
                  <el-input v-model="getLandTourPriceById.baby" />
                </el-form-item>
                <el-form-item label="Phụ phí">
                  <el-input v-model="getLandTourPriceById.surcharge" />
                </el-form-item>
              </CCol>
            </CRow>
          </el-collapse-item>
          <el-collapse-item title="Lịch trình chi tiết" name="2">
            <el-scrollbar max-height="400px">
              <div style="text-align: center">
                <el-icon
                  color="#409EFC"
                  class="no-inherit"
                  :size="25"
                  style="margin-right: 20px"
                  @click="onClickPlusSchedule"
                >
                  <CirclePlusFilled />
                </el-icon>
                <el-icon
                  color="#409EFC"
                  class="no-inherit"
                  :size="25"
                  @click="onClickPopSchedule"
                  ><RemoveFilled
                /></el-icon>
              </div>
              <div v-for="item in dataSchedule" :key="item.id">
                <CRow>
                  <CCol>
                    <el-input
                      v-model="item.alias"
                      style="width: 100px; margin-left: 15px"
                    />
                  </CCol>
                  <CCol> </CCol>
                </CRow>
                <CRow>
                  <CCol>
                    <el-form-item label="Thời gian">
                      <el-input type="date" v-model="item.time" />
                    </el-form-item>
                  </CCol>
                  <CCol>
                    <el-form-item label="Địa điểm">
                      <el-input v-model="item.location" />
                    </el-form-item>
                  </CCol>
                </CRow>
                <CRow>
                  <CCol>
                    <el-form-item label="Chi tiết lịch trình">
                      <el-input
                        type="textarea"
                        v-model="item.detail"
                        :rows="6"
                      />
                    </el-form-item>
                  </CCol>
                </CRow>
              </div>
            </el-scrollbar>
          </el-collapse-item>
          <el-collapse-item title="Khuyến mãi" name="3">
            <CRow>
              <CCol>
                <el-form-item label="Từ ngày">
                  <el-input type="date" v-model="discountTour.startDate" />
                </el-form-item>
              </CCol>
              <CCol>
                <el-form-item label="Đến ngày">
                  <el-input type="date" v-model="discountTour.endDate" />
                </el-form-item>
              </CCol>
              <CCol>
                <el-form-item label="Khuyến mại (%)">
                  <el-input type="number" v-model="discountTour.discount" />
                </el-form-item>
              </CCol>
            </CRow>
          </el-collapse-item>
          <!-- <el-collapse-item title="Lịch sử book tour" name="4">
            <CRow>
              <CCol>
                <el-form-item label="Từ ngày">
                  <el-input type="date" v-model="fromDatetoOrder" />
                </el-form-item>
              </CCol>
              <CCol>
                <el-form-item label="Đến ngày">
                  <el-input type="date" v-model="toDatetoOrder" />
                </el-form-item>
              </CCol>
              <CCol>
                <el-button type="primary" @click="onClickSearchOrdersByDate"
                  >Tìm kiếm</el-button
                >
              </CCol>
            </CRow>
          </el-collapse-item> -->
          <el-collapse-item title="Người hướng dẫn" name="5">
            <el-form-item label="Chọn người hướng dẫn">
              <el-select
                v-model="tourDataDetail.guideId"
                placeholder="Chọn người hướng dẫn"
                style="width: 80%"
              >
                <el-option
                  v-for="item in getGuideSelectBoxList"
                  :key="item.id"
                  :label="item.info"
                  :value="item.id"
                >
                </el-option>
              </el-select>
            </el-form-item>
          </el-collapse-item>
        </el-collapse>
      </CCol>
    </CRow>
    <CRow style="margin-top: 20px">
      <CCol>
        <div style="float: right">
          <el-button type="danger" @click="closeDialogLog">Đóng</el-button>
          <el-button type="success" @click="updateDataClick">Lưu</el-button>
        </div>
      </CCol>
    </CRow>
  </el-form>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'
export default {
  props: {
    tourData: Object,
    dataSchedule: [],
    disCountTourGet: Object,
  },
  watch: {
    tourData: {
      immediate: true,
      deep: true,
      handler: function () {
        this.tourDataDetail = Object.assign({}, this.tourData)
      },
    },
    dataSchedule: {
      immediate: true,
      deep: true,
      handler: function () {
        this.tourSchedule = this.dataSchedule
      },
    },
  },
  data() {
    return {
      //tourDataDetail: {},
      tourSchedule: [],
      objectSearch: {
        id: null,
        name: null,
      },
      // discountTour: {},
      fromDatetoOrder: null,
      toDatetoOrder: null,
      orderList: [],
      tourDataDetail: {},
      statusList: [
        {
          id: 0,
          value: 'Không hoạt động',
        },
        {
          id: 1,
          value: 'Hoạt động',
        },
      ],
      tourDataAdd: {
        id: null,
        code: null,
        name: null,
        startTime: null,
        period: null,
        startPlaceId: null,
        endPlaceId: null,
        mainImageUrl: null,
        shortDesc: null,
        placeOrderMax: null,
        status: null,

        idTourPrice: null,
        tourPriceAdult: null,
        tourPriceChildren: null,
        tourPriceKid: null,
        tourPriceBaby: null,
        tourPriceSurcharge: null,

        idLandTourPrice: null,
        landtourPriceAdult: null,
        landtourPriceChildren: null,
        landtourPriceKid: null,
        landtourPriceBaby: null,
        landtourPriceSurcharge: null,

        tourScheduleDTOList: [],

        idDiscount: null,
        discountStartDate: null,
        discountEndDate: null,
        priceId: null,
        discount: null,

        guideId: null,
      },
    }
  },
  computed: {
    ...mapGetters({
      placeListGet: 'tour/getPlaceList',
      getLandTourPriceById: 'tour/getLandPrice1TourList',
      getTourPriceById: 'tour/getTourPrice1TourList',
      getTourScheduleByTourId: 'tour/getTourScheduleList',
      getDiscountTour: 'tour/getDiscountTour',
      getListOrder: 'tour/getOrdersById',
      getGuideSelectBoxList: 'guide/getGuideSelectBoxList',
    }),
    discountTour() {
      return this.disCountTourGet
    },
    listOrder() {
      return this.getListOrder()
    },
  },
  created() {
    this.actionPlaceList()
    // this.tourDataDetail = console.log(this.getDiscountTour)

    this.getSchedule()
    // this.getDiscount()
    console.log(this.tourData)
  },

  methods: {
    ...mapActions({
      actionPlaceList: 'tour/actionPlaceList',
      actionTourList: 'tour/actionTourList',
      actionPostUpdateTour: 'tour/actionPostUpdateTour',
    }),
    getFormattedDate(date) {
      let year = date.getFullYear()
      let month = (1 + date.getMonth()).toString().padStart(2, '0')
      let day = date.getDate().toString().padStart(2, '0')

      return year + '-' + month + '-' + day
    },
    getSchedule() {
      this.tourSchedule = this.dataSchedule
      console.log(this.tourSchedule)
    },
    onClickPlusSchedule() {
      let count = this.tourSchedule.length
      let ngay = 'Ngày ' + (count + 1)
      var t = {
        alias: ngay,
        time: null,
        location: null,
        detail: null,
      }
      this.tourSchedule.push(t)
    },
    onClickPopSchedule() {
      this.tourSchedule.pop()
    },
    updateDataClick() {
      console.log(this.tourDataDetail)
      console.log(this.discountTour)
      console.log(this.getLandTourPriceById)
      console.log(this.getTourPriceById)
      console.log(this.dataSchedule)

      this.tourDataAdd.id = this.tourDataDetail.id
      this.tourDataAdd.code = this.tourDataDetail.code
      this.tourDataAdd.name = this.tourDataDetail.name
      if (this.tourDataDetail.startTime != null) {
        this.tourDataAdd.startTime = this.tourDataDetail.startTime.toString()
      }
      this.tourDataAdd.period = this.tourDataDetail.period
      this.tourDataAdd.startPlaceId = this.tourDataDetail.startPlaceId
      this.tourDataAdd.endPlaceId = this.tourDataDetail.endPlaceId
      this.tourDataAdd.mainImageUrl = this.tourDataDetail.mainImageUrl
      this.tourDataAdd.shortDesc = this.tourDataDetail.shortDesc
      this.tourDataAdd.placeOrderMax = this.tourDataDetail.placeOrderMax
      this.tourDataAdd.status = this.tourDataDetail.status
      this.tourDataAdd.guideId = this.tourDataDetail.guideId

      this.tourDataAdd.idTourPrice = this.getTourPriceById.id
      this.tourDataAdd.tourPriceAdult = this.getTourPriceById.adult
      this.tourDataAdd.tourPriceChildren = this.getTourPriceById.children
      this.tourDataAdd.tourPriceKid = this.getTourPriceById.kid
      this.tourDataAdd.tourPriceBaby = this.getTourPriceById.baby
      this.tourDataAdd.tourPriceSurcharge = this.getTourPriceById.surcharge

      this.tourDataAdd.idLandTourPrice = this.getLandTourPriceById.id
      this.tourDataAdd.landtourPriceAdult = this.getLandTourPriceById.adult
      this.tourDataAdd.landtourPriceChildren =
        this.getLandTourPriceById.children
      this.tourDataAdd.landtourPriceKid = this.getLandTourPriceById.kid
      this.tourDataAdd.landtourPriceBaby = this.getLandTourPriceById.baby
      this.tourDataAdd.landtourPriceSurcharge =
        this.getLandTourPriceById.surcharge
      if (this.discountTour.idDiscount != null) {
        this.tourDataAdd.idDiscount = this.discountTour.id.toString()
      }
      if (this.discountTour.startDate != null) {
        this.tourDataAdd.discountStartDate =
          this.discountTour.startDate.toString()
      }
      if (this.discountTour.endDate != null) {
        this.tourDataAdd.discountEndDate = this.discountTour.endDate.toString()
      }
      this.tourDataAdd.priceId = this.discountTour.priceId
      this.tourDataAdd.discount = this.discountTour.discount
      this.tourDataAdd.tourScheduleDTOList = this.tourSchedule
      this.actionPostUpdateTour(this.tourDataAdd)
      console.log(this.tourDataAdd)
      this.actionTourList(this.objectSearch)
      this.closeDialogLog()
      this.$notify({
        title: 'Thành công',
        message: 'Cập nhật thành công',
        type: 'success',
      })
    },
    closeDialogLog() {
      console.log('click')
      this.$emit('close-dialog-update', 'false')
    },
    // getDiscount() {
    //   this.discountTour = this.disCountTourGet

    //   console.log('abccc' + this.disCountTourGet.startDate)
    //   console.log('abccc' + this.discountTour.startDate)
    // },
  },
  onClickSearchOrdersByDate() {},
}
</script>
<style scoped>
.scrollbar-demo-item {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 50px;
  margin: 10px;
  text-align: center;
  border-radius: 4px;
}
</style>
