<template>
  <el-form label-width="160px">
    <CRow>
      <CCol>
        <el-form-item label="Id">
          <el-input type="number" v-model="guideAdd.id" disabled />
        </el-form-item>
      </CCol>
    </CRow>
    <CRow>
      <CCol>
        <el-form-item label="Họ và tên">
          <el-input v-model="guideAdd.name" />
        </el-form-item>
      </CCol>
    </CRow>
    <CRow>
      <CCol>
        <el-form-item label="Số điện thoại">
          <el-input v-model="guideAdd.phone" />
        </el-form-item>
      </CCol>
    </CRow>
    <CRow>
      <CCol>
        <el-form-item label="Địa chỉ">
          <el-input v-model="guideAdd.address" />
        </el-form-item>
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
  props: {
    dataGuide: Object,
  },
  watch: {
    dataGuide: {
      immediate: true,
      deep: true,
      handler: function () {
        this.guideAdd.id = this.dataGuide.id + 1
      },
    },
  },
  data() {
    return {
      guideAdd: {
        id: null,
        name: null,
        phone: null,
        address: null,
      },
      objectSearch: {
        nameGuide: null,
        phoneGuide: null,
      },
    }
  },
  computed: {
    // ...mapGetters({
    //   placeListGet: 'tour/getPlaceList',
    //   getLandTourPriceById: 'tour/getLandPrice1TourList',
    //   getTourPriceById: 'tour/getTourPrice1TourList',
    //   getTourScheduleByTourId: 'tour/getTourScheduleList',
    //   getDiscountTour: 'tour/getDiscountTour',
    //   getListOrder: 'tour/getOrdersById',
    // }),
    ...mapGetters({
      getMaxId: 'guide/getMaxId',
    }),
  },
  created() {},

  methods: {
    ...mapActions({
      actionPlaceList: 'tour/actionPlaceList',
      actionPostNewGuide: 'guide/actionPostNewGuide',
      actionGuideList: 'guide/actionGuideList',
    }),
    onClickSearchOrdersByDate() {},
    closeDialogLog() {
      console.log('click')
      this.$emit('close-dialog', 'false')
    },
    async addDataClick() {
      var str = 'Chưa điền '
      if (this.guideAdd.name == null || this.guideAdd.name == '') {
        str += 'tên,'
      }
      if (this.guideAdd.phone == null || this.guideAdd.phone == '') {
        str += 'số điện thoại,'
      }
      if (this.guideAdd.address == null || this.guideAdd.address == '') {
        str += 'địa chỉ,'
      }
      if (str != 'Chưa điền ') {
        this.$notify({
          title: 'Cảnh báo',
          message: str,
          type: 'warning',
        })
      } else {
        console.log(this.guideAdd)
        await this.actionPostNewGuide(this.guideAdd)
        await this.actionGuideList(this.objectSearch)
        this.closeDialogLog()
        this.$notify({
          title: 'Thành công',
          message: 'Thêm mới thành công',
          type: 'success',
        })
      }
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
