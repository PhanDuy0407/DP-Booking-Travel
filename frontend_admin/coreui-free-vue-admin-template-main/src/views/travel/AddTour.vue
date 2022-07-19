<template>
  <el-form label-width="160px">
    <CRow>
      <CCol>
        <el-form-item label="Mã code">
          <el-input v-model="tourData.code" />
        </el-form-item>
      </CCol>
      <CCol>
        <el-form-item label="Tên tour">
          <el-input v-model="tourData.name" />
        </el-form-item>
      </CCol>
    </CRow>
    <CRow>
      <CCol>
        <el-form-item label="Thời gian bắt đầu">
          <el-input v-model="tourData.startTime" type="date" />
        </el-form-item>
      </CCol>
      <CCol>
        <el-form-item label="Khoảng thời gian">
          <el-input v-model="tourData.period" />
        </el-form-item>
      </CCol>
    </CRow>
    <CRow>
      <CCol>
        <el-form-item label="Địa điểm bắt đầu">
          <el-select
            v-model="tourData.startPlaceId"
            class="m-2"
            placeholder="Select"
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
            v-model="tourData.endPlaceId"
            class="m-2"
            placeholder="Select"
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
        <el-form-item label="URL ảnh">
          <el-input v-model="tourData.mainImageUrl" />
        </el-form-item>
      </CCol>
    </CRow>
    <CRow>
      <CCol>
        <el-form-item label="Mô tả">
          <el-input type="textarea" v-model="tourData.shortDesc" :rows="6" />
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
                  <el-input v-model="tourPriceById.adult" />
                </el-form-item>
                <el-form-item label="Giá trẻ em 5-11 tuổi">
                  <el-input v-model="tourPriceById.children" />
                </el-form-item>
                <el-form-item label="Giá trẻ em 2-5 tuổi">
                  <el-input v-model="tourPriceById.kid" />
                </el-form-item>
                <el-form-item label="Giá trẻ em <2 tuổi">
                  <el-input v-model="tourPriceById.baby" />
                </el-form-item>
                <el-form-item label="Phụ phí">
                  <el-input v-model="tourPriceById.surcharge" />
                </el-form-item>
              </CCol>
              <CCol>
                <h6 style="text-align: center">Giá land tour</h6>
                <el-form-item label="Giá người trưởng thành">
                  <el-input v-model="landTourPriceById.adult" />
                </el-form-item>
                <el-form-item label="Giá trẻ em 5-11 tuổi">
                  <el-input v-model="landTourPriceById.children" />
                </el-form-item>
                <el-form-item label="Giá trẻ em 2-5 tuổi">
                  <el-input v-model="landTourPriceById.kid" />
                </el-form-item>
                <el-form-item label="Giá trẻ em <2 tuổi">
                  <el-input v-model="landTourPriceById.baby" />
                </el-form-item>
                <el-form-item label="Phụ phí">
                  <el-input v-model="landTourPriceById.surcharge" />
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
              <div v-for="item in tourSchedule" :key="item.id">
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
                v-model="tourData.guideId"
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
          <el-button type="success" @click="addDataClick">Lưu</el-button>
        </div>
      </CCol>
    </CRow>
  </el-form>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'

export default {
  data() {
    return {
      //tourDataDetail: {},
      objectSearch: {
        id: null,
        name: null,
      },
      tourData: {},
      tourSchedule: [
        {
          alias: 'Ngày 1',
          time: null,
          location: null,
          detail: null,
        },
      ],
      discountTour: {},
      fromDatetoOrder: null,
      toDatetoOrder: null,
      orderList: [],
      tourPriceById: {},
      landTourPriceById: {},

      tourDataAdd: {
        code: null,
        name: null,
        startTime: null,
        period: null,
        startPlaceId: null,
        endPlaceId: null,
        mainImageUrl: null,
        shortDesc: null,

        tourPriceAdult: null,
        tourPriceChildren: null,
        tourPriceKid: null,
        tourPriceBaby: null,
        tourPriceSurcharge: null,

        landtourPriceAdult: null,
        landtourPriceChildren: null,
        landtourPriceKid: null,
        landtourPriceBaby: null,
        landtourPriceSurcharge: null,

        tourScheduleDTOList: [],

        discountStartDate: null,
        discountEndDate: null,
        discount: null,

        guideId: null,
      },
    }
  },
  computed: {
    ...mapGetters({
      placeListGet: 'tour/getPlaceList',
      getTourScheduleByTourId: 'tour/getTourScheduleList',
      getListOrder: 'tour/getOrdersById',
      getGuideSelectBoxList: 'guide/getGuideSelectBoxList',
      getDataAdd: 'tour/getDataAdd',
    }),
    listOrder() {
      return this.getListOrder()
    },
  },
  created() {
    this.actionPlaceList()
  },

  methods: {
    ...mapActions({
      actionTourList: 'tour/actionTourList',
      actionPlaceList: 'tour/actionPlaceList',
      actionPostNewTour: 'tour/actionPostNewTour',
    }),
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
    getFormattedDate(date) {
      let year = date.getFullYear()
      let month = (1 + date.getMonth()).toString().padStart(2, '0')
      let day = date.getDate().toString().padStart(2, '0')

      return year + '-' + month + '-' + day
    },
    onClickSearchOrdersByDate() {},
    closeDialogLog() {
      console.log('click')
      this.$emit('close-dialog', 'false')
    },
    addDataClick() {
      this.tourDataAdd.code = this.tourData.code
      this.tourDataAdd.name = this.tourData.name
      if (this.tourData.startTime != null) {
        this.tourDataAdd.startTime = this.tourData.startTime.toString()
      }
      this.tourDataAdd.period = this.tourData.period
      this.tourDataAdd.startPlaceId = this.tourData.startPlaceId
      this.tourDataAdd.endPlaceId = this.tourData.endPlaceId
      this.tourDataAdd.mainImageUrl = this.tourData.mainImageUrl
      this.tourDataAdd.shortDesc = this.tourData.shortDesc
      this.tourDataAdd.guideId = this.tourData.guideId

      this.tourDataAdd.tourPriceAdult = this.tourPriceById.adult
      this.tourDataAdd.tourPriceChildren = this.tourPriceById.children
      this.tourDataAdd.tourPriceKid = this.tourPriceById.kid
      this.tourDataAdd.tourPriceBaby = this.tourPriceById.baby
      this.tourDataAdd.tourPriceSurcharge = this.tourPriceById.surcharge

      this.tourDataAdd.landtourPriceAdult = this.landTourPriceById.adult
      this.tourDataAdd.landtourPriceChildren = this.landTourPriceById.children
      this.tourDataAdd.landtourPriceKid = this.landTourPriceById.kid
      this.tourDataAdd.landtourPriceBaby = this.landTourPriceById.baby
      this.tourDataAdd.landtourPriceSurcharge = this.landTourPriceById.surcharge
      if (this.discountTour.startDate != null) {
        this.tourDataAdd.discountStartDate =
          this.discountTour.startDate.toString()
      }
      if (this.discountTour.endDate != null) {
        this.tourDataAdd.discountEndDate = this.discountTour.endDate.toString()
      }

      this.tourDataAdd.discount = this.discountTour.discount
      this.tourDataAdd.tourScheduleDTOList = this.tourSchedule
      this.actionPostNewTour(this.tourDataAdd)
      console.log(this.getDataAdd)
      this.actionTourList(this.objectSearch)
      this.closeDialogLog()
      this.$notify({
        title: 'Thành công',
        message: 'Thêm mới thành công',
        type: 'success',
      })
    },
  },
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
