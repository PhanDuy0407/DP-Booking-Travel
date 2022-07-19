<template>
  <div>
    <CRow>
      <CCol>
        <el-form-item label="Từ ngày">
          <el-input type="date" v-model="objectSearch.fromDate" />
        </el-form-item>
      </CCol>
      <CCol>
        <el-form-item label="Đến ngày">
          <el-input type="date" v-model="objectSearch.toDate" />
        </el-form-item>
      </CCol>
      <CCol>
        <el-button @click="handleSearchClick" type="primary"
          >Tìm kiếm</el-button
        >
      </CCol>
    </CRow>
    <el-table
      :data="displayOrdersAllData"
      stripe
      border
      style="width: 100%"
      v-loading="loading"
      :summary-method="getSummaries"
      show-summary
    >
      <el-table-column type="index" fixed />
      <el-table-column prop="id" label="Id" />
      <el-table-column prop="fullname" label="Họ và tên" />
      <el-table-column prop="email" label="Email" />
      <el-table-column prop="phoneNumber" label="Số điện thoại" />
      <el-table-column prop="address" label="Địa chỉ" />
      <el-table-column prop="createdDate" label="Ngày đặt đơn">
        <template #default="scope">
          {{ getFormattedDate(new Date(scope.row.createdDate)) }}
        </template>
      </el-table-column>
      <el-table-column prop="sumPrice" label="Tổng đơn" />
      <el-table-column fixed="right" label="Thao tác">
        <template #default="scope">
          <el-button
            link
            type="primary"
            size="small"
            @click="handleUpdateClick(scope.row)"
            >Chi tiết</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      v-model:currentPage="currentPage2"
      v-model:page-size="pageSize2"
      :page-sizes="[10, 20, 50, 100]"
      :small="small"
      :disabled="disabled"
      :background="background"
      layout="sizes, prev, pager, next"
      :total="displayOrdersAllData.length"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      style="float: right"
    />
    <el-dialog v-model="dialogDetailVisible" title="Chi tiết đơn đặt tour">
      <OrderDetail
        :dataOrderDetail="guideUpdate"
        @close-dialog="closeDialog"
      ></OrderDetail>
    </el-dialog>
  </div>
</template>
<script>
import { mapGetters, mapActions } from 'vuex'
import OrderDetail from './OrderDetail.vue'
export default {
  name: 'Quản lý đơn đặt tour',
  components: { OrderDetail },
  data() {
    return {
      currentPage2: 1,
      pageSize2: 10,
      dialogDetailVisible: false,
      objectSearch: {
        fromDate: null,
        toDate: null,
      },
      guideUpdate: {},
    }
  },
  computed: {
    ...mapGetters({
      getAllOrdersList: 'order/getAllOrdersList',
    }),
    displayOrdersAllData() {
      if (!this.getAllOrdersList || this.getAllOrdersList.length === 0)
        return []
      return this.getAllOrdersList.slice(
        this.pageSize2 * this.currentPage2 - this.pageSize2,
        this.pageSize2 * this.currentPage2,
      )
    },
  },
  created() {
    this.actionAllOrdersList(this.objectSearch)
  },
  methods: {
    ...mapActions({
      actionAllOrdersList: 'order/actionAllOrdersList',
      actionLandTourPriceById: 'tour/actionGetLandPrice1TourList',
      actionTourPriceById: 'tour/actionGetTourPrice1TourList',
      actionGetDiscountTour: 'tour/actionGetDiscountTour',
    }),
    handleAddClick() {},
    handleSearchClick() {
      console.log(this.objectSearch)
      var fromDate = this.objectSearch.fromDate
      var toDate = this.objectSearch.toDate
      if (this.objectSearch.fromDate != null) {
        this.objectSearch.fromDate = this.getFormattedDateForOrder(
          new Date(this.objectSearch.fromDate),
        )
      }
      if (this.objectSearch.toDate != null) {
        this.objectSearch.toDate = this.getFormattedDateForOrder(
          new Date(this.objectSearch.toDate),
        )
      }
      this.actionAllOrdersList(this.objectSearch)
      this.objectSearch.fromDate = fromDate
      this.objectSearch.toDate = toDate
    },
    getFormattedDateForOrder(date) {
      let year = date.getFullYear()
      let month = (1 + date.getMonth()).toString().padStart(2, '0')
      let day = date.getDate().toString().padStart(2, '0')

      return year + '/' + month + '/' + day
    },
    getFormattedDate(date) {
      let year = date.getFullYear()
      let month = (1 + date.getMonth()).toString().padStart(2, '0')
      let day = date.getDate().toString().padStart(2, '0')

      return day + '/' + month + '/' + year
    },
    handleUpdateClick(data) {
      this.dialogDetailVisible = true
      this.guideUpdate = data
      this.actionLandTourPriceById(data.priceId)
      this.actionTourPriceById(data.priceId)
      this.actionGetDiscountTour(data.priceId)
    },
    handleCurrentChange(val) {
      this.currentPage2 = val
    },
    handleSizeChange(val) {
      this.pageSize2 = val
    },
    closeDialog(e) {
      this.dialogDetailVisible = false
      console.log(e)
    },
    getSummaries(param) {
      const { columns, data } = param
      const sums = []
      columns.forEach((column, index) => {
        if (index === 1) {
          sums[index] = 'Tổng tiền'
          return
        }
        const values = data.map((item) => Number(item[column.property]))
        if (!values.every((value) => isNaN(value)) && index == 7) {
          sums[index] = values.reduce((prev, curr) => {
            const value = Number(curr)
            if (!isNaN(value)) {
              return prev + curr
            } else {
              return prev
            }
          }, 0)
        } else {
          sums[index] = ''
        }
      })

      return sums
    },
  },
}
</script>
