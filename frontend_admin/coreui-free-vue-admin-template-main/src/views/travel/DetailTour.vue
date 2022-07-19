<template>
  <el-form label-width="160px">
    <CRow>
      <CCol>
        <el-form-item label="Mã code">
          <el-input v-model="tourDataDetail.code" disabled="true" />
        </el-form-item>
      </CCol>
      <CCol>
        <el-form-item label="Tên tour">
          <el-input v-model="tourDataDetail.name" disabled="true" />
        </el-form-item>
      </CCol>
    </CRow>
    <CRow>
      <CCol>
        <el-form-item label="Thời gian bắt đầu">
          <el-input
            v-model="tourDataDetail.startTime"
            type="date"
            disabled="true"
          />
        </el-form-item>
      </CCol>
      <CCol>
        <el-form-item label="Khoảng thời gian">
          <el-input v-model="tourDataDetail.period" disabled="true" />
        </el-form-item>
      </CCol>
    </CRow>
    <CRow>
      <CCol>
        <el-form-item label="Địa điểm bắt đầu">
          <el-select
            v-model="tourDataDetail.startPlaceId"
            class="m-2"
            placeholder="Select"
            disabled="true"
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
            placeholder="Select"
            disabled="true"
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
          <el-input v-model="tourDataDetail.mainImageUrl" disabled="true" />
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
            disabled="true"
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
                  <el-input v-model="getTourPriceById.adult" disabled="true" />
                </el-form-item>
                <el-form-item label="Giá trẻ em 12-18 tuổi">
                  <el-input
                    v-model="getTourPriceById.children"
                    disabled="true"
                  />
                </el-form-item>
                <el-form-item label="Giá trẻ em 2-12 tuổi">
                  <el-input v-model="getTourPriceById.kid" disabled="true" />
                </el-form-item>
                <el-form-item label="Giá trẻ em <2 tuổi">
                  <el-input v-model="getTourPriceById.baby" disabled="true" />
                </el-form-item>
                <el-form-item label="Phụ phí">
                  <el-input
                    v-model="getTourPriceById.surcharge"
                    disabled="true"
                  />
                </el-form-item>
              </CCol>
              <CCol>
                <h6 style="text-align: center">Giá land tour</h6>
                <el-form-item label="Giá người trưởng thành">
                  <el-input
                    v-model="getLandTourPriceById.adult"
                    disabled="true"
                  />
                </el-form-item>
                <el-form-item label="Giá trẻ em 12-18 tuổi">
                  <el-input
                    v-model="getLandTourPriceById.children"
                    disabled="true"
                  />
                </el-form-item>
                <el-form-item label="Giá trẻ em 2-12 tuổi">
                  <el-input
                    v-model="getLandTourPriceById.kid"
                    disabled="true"
                  />
                </el-form-item>
                <el-form-item label="Giá trẻ em <2 tuổi">
                  <el-input
                    v-model="getLandTourPriceById.baby"
                    disabled="true"
                  />
                </el-form-item>
                <el-form-item label="Phụ phí">
                  <el-input
                    v-model="getLandTourPriceById.surcharge"
                    disabled="true"
                  />
                </el-form-item>
              </CCol>
            </CRow>
          </el-collapse-item>
          <el-collapse-item title="Lịch trình chi tiết" name="2">
            <el-scrollbar max-height="400px">
              <div v-for="item in dataSchedule" :key="item.id">
                <CRow>
                  <CCol>
                    <el-input
                      v-model="item.alias"
                      disabled="true"
                      style="width: 100px; margin-left: 15px"
                    />
                  </CCol>
                  <CCol> </CCol>
                </CRow>
                <CRow>
                  <CCol>
                    <el-form-item label="Thời gian">
                      <el-input
                        type="date"
                        v-model="item.time"
                        disabled="true"
                      />
                    </el-form-item>
                  </CCol>
                  <CCol>
                    <el-form-item label="Địa điểm">
                      <el-input v-model="item.location" disabled="true" />
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
                        disabled="true"
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
                  <el-input
                    type="date"
                    v-model="discountTour.startDate"
                    disabled="true"
                  />
                </el-form-item>
              </CCol>
              <CCol>
                <el-form-item label="Đến ngày">
                  <el-input
                    type="date"
                    v-model="discountTour.endDate"
                    disabled="true"
                  />
                </el-form-item>
              </CCol>
              <CCol>
                <el-form-item label="Khuyến mại (%)">
                  <el-input
                    type="number"
                    v-model="discountTour.discount"
                    disabled="true"
                  />
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
  data() {
    return {
      //tourDataDetail: {},
      tourSchedule: [],
      // discountTour: {},
      fromDatetoOrder: null,
      toDatetoOrder: null,
      orderList: [],
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
    tourDataDetail() {
      return Object.assign({}, this.tourData)
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
  },

  methods: {
    ...mapActions({
      actionPlaceList: 'tour/actionPlaceList',
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
