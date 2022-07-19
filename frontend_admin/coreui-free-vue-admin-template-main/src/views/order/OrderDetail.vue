<template>
  <el-form label-width="160px">
    <CRow>
      <CCol>
        <el-form-item label="Họ và tên">
          <el-input v-model="dataOrder.fullname" disabled="true" />
        </el-form-item>
      </CCol>
      <CCol>
        <el-form-item label="Email">
          <el-input v-model="dataOrder.email" disabled="true" />
        </el-form-item>
      </CCol>
    </CRow>
    <CRow>
      <CCol>
        <el-form-item label="Số điện thoại">
          <el-input v-model="dataOrder.phoneNumber" disabled="true" />
        </el-form-item>
      </CCol>
      <CCol>
        <el-form-item label="Địa chỉ">
          <el-input v-model="dataOrder.address" disabled="true" />
        </el-form-item>
      </CCol>
    </CRow>
    <CRow>
      <CCol>
        <h6 style="text-align: center">Số lượng người</h6>
        <el-form-item label="Người trưởng thành">
          <el-input v-model="dataOrder.adultCount" disabled="true" />
        </el-form-item>
        <el-form-item label="Trẻ em 5-11 tuổi">
          <el-input v-model="dataOrder.childrenCount" disabled="true" />
        </el-form-item>
        <el-form-item label="Trẻ em 2-5 tuổi">
          <el-input v-model="dataOrder.kidCount" disabled="true" />
        </el-form-item>
        <el-form-item label="Trẻ em <2 tuổi">
          <el-input v-model="dataOrder.babyCount" disabled="true" />
        </el-form-item>
      </CCol>
      <CCol>
        <h6 style="text-align: center">Giá tour</h6>
        <el-form-item label="Giá người trưởng thành">
          <el-input v-model="getTourPriceById.adult" disabled="true" />
        </el-form-item>
        <el-form-item label="Giá trẻ em 5-11 tuổi">
          <el-input v-model="getTourPriceById.children" disabled="true" />
        </el-form-item>
        <el-form-item label="Giá trẻ em 2-5 tuổi">
          <el-input v-model="getTourPriceById.kid" disabled="true" />
        </el-form-item>
        <el-form-item label="Giá trẻ em <2 tuổi">
          <el-input v-model="getTourPriceById.baby" disabled="true" />
        </el-form-item>
      </CCol>
      <CCol>
        <h6 style="text-align: center">Giá land tour</h6>
        <el-form-item label="Giá người trưởng thành">
          <el-input v-model="getLandTourPriceById.adult" disabled="true" />
        </el-form-item>
        <el-form-item label="Giá trẻ em 5-11 tuổi">
          <el-input v-model="getLandTourPriceById.children" disabled="true" />
        </el-form-item>
        <el-form-item label="Giá trẻ em 2-5 tuổi">
          <el-input v-model="getLandTourPriceById.kid" disabled="true" />
        </el-form-item>
        <el-form-item label="Giá trẻ em <2 tuổi">
          <el-input v-model="getLandTourPriceById.baby" disabled="true" />
        </el-form-item>
      </CCol>
    </CRow>
    <CRow>
      <CCol>
        <el-form-item label="Tổng tiền">
          <el-input v-model="dataOrder.sumPrice" disabled="true" />
        </el-form-item>
      </CCol>
    </CRow>
  </el-form>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'
export default {
  props: {
    dataOrderDetail: Object,
  },
  watch: {
    dataOrderDetail: {
      immediate: true,
      deep: true,
      handler: function () {
        this.dataOrder = this.dataOrderDetail
      },
    },
  },
  data() {
    return {
      dataOrder: {},
      fromDatetoOrder: null,
      toDatetoOrder: null,
      orderList: [],
    }
  },
  computed: {
    ...mapGetters({
      getLandTourPriceById: 'tour/getLandPrice1TourList',
      getTourPriceById: 'tour/getTourPrice1TourList',
      getDiscountTour: 'tour/getDiscountTour',
      getListOrder: 'tour/getOrdersById',
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
  created() {},

  methods: {
    ...mapActions({}),
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
