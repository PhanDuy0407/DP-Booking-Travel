<template>
  <div>
    <el-button
      @click="handleAddClick"
      type="primary"
      style="margin-bottom: 20px"
      >Thêm mới</el-button
    >
    <CRow>
      <CCol>
        <el-form-item label="Mã Tour">
          <el-input v-model="objectSearch.id" placeholder="Tìm kiếm mã Tour" />
        </el-form-item>
      </CCol>
      <CCol>
        <el-form-item label="Tên Tour">
          <el-input
            v-model="objectSearch.name"
            placeholder="Tìm kiếm theo tên Tour"
          />
        </el-form-item>
      </CCol>
      <CCol>
        <el-button @click="handleSearchClick" type="primary"
          >Tìm kiếm</el-button
        >
      </CCol>
    </CRow>
    <el-table
      :data="displayData"
      stripe
      border
      style="width: 100%"
      v-loading="loading"
    >
      <el-table-column type="index" fixed />
      <el-table-column prop="code" label="Mã Tour" />
      <el-table-column prop="name" label="Tên Tour" />
      <el-table-column prop="startTime" label="Thời gian bắt đầu">
        <template #default="scope">
          {{ getFormattedDate(new Date(scope.row.startTime)) }}
        </template>
      </el-table-column>
      <el-table-column prop="period" label="Khoảng thời gian" />
      <el-table-column prop="mainImageUrl" label="Link ảnh" />
      <el-table-column prop="placeOrderMax" label="Giới hạn đặt" />
      <el-table-column prop="status" label="Trạng thái" />
      <el-table-column fixed="right" label="Thao tác">
        <template #default="scope">
          <el-button
            link
            type="primary"
            size="small"
            @click="handleDetailClick(scope.row)"
            >Detail</el-button
          >
          <el-button
            link
            type="primary"
            size="small"
            @click="handleUpdateClick(scope.row)"
            >Edit</el-button
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
      :total="list.length"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      style="float: right"
    />
    <el-dialog
      v-model="dialogDetailVisible"
      title="Thông tin chi tiết Tour"
      width="80%"
    >
      <detail-tour
        :tourData="dataTour"
        :dataSchedule="dataSchedule"
        :disCountTourGet="discountTour"
      ></detail-tour>
    </el-dialog>
    <el-dialog v-model="dialogUpdateVisible" title="Cập nhật thông tin Tour">
      <UpdateTour
        @close-dialog-update="closeDialogUpdate"
        :tourData="dataTour"
        :dataSchedule="dataSchedule"
        :disCountTourGet="discountTour"
      ></UpdateTour>
    </el-dialog>
    <el-dialog v-model="dialogAddVisible" title="Thêm mới Tour">
      <AddTour @close-dialog="closeDialogAdd"></AddTour>
    </el-dialog>
  </div>
</template>
<script>
import { mapGetters, mapActions } from 'vuex'
import DetailTour from './DetailTour.vue'
import AddTour from './AddTour.vue'
import UpdateTour from './UpdateTour.vue'
import { ref } from 'vue'

export default {
  components: { DetailTour, AddTour, UpdateTour },
  name: 'Quản lý Tour',
  data() {
    return {
      currentPage2: 1,
      pageSize2: 10,
      loading: ref(false),
      list: [],
      index: 0,
      dialogDetailVisible: false,
      dialogAddVisible: false,
      dialogUpdateVisible: false,
      dataTour: {
        id: null,
        name: '',
        shortDesc: '',
        startTime: '',
        period: '',
        startPlaceId: null,
        endPlaceId: null,
        mainImageUrl: '',
        guideId: null,
        placeOrderMax: null,
        code: '',
        status: null,
      },
      dataSchedule: [],
      discountTour: {},
      objectSearch: {
        id: null,
        name: null,
      },
    }
  },
  created() {
    this.getData()
    this.actionGetGuideSelectBoxList()
  },
  // watch: {
  //   list: {
  //     immediate: true,
  //     deep: true,
  //     handler: function () {
  //       this.list = this.tourListGet
  //     },
  //   },
  // },
  computed: {
    ...mapGetters({
      tourListGet: 'tour/getTourList',
      getTourScheduleByTourId: 'tour/getTourScheduleList',
      getDiscountTour: 'tour/getDiscountTour',
    }),
    displayData() {
      if (!this.list || this.list.length === 0) return []
      return this.list.slice(
        this.pageSize2 * this.currentPage2 - this.pageSize2,
        this.pageSize2 * this.currentPage2,
      )
    },
  },
  methods: {
    ...mapActions({
      actionTourList: 'tour/actionTourList',
      actionTourPriceById: 'tour/actionGetTourPrice1TourList',
      actionLandTourPriceById: 'tour/actionGetLandPrice1TourList',
      actionGetTourScheduleById: 'tour/actionGetTourScheduleList',
      actionGetDiscountTour: 'tour/actionGetDiscountTour',
      actionGetOrdersByTourId: 'tour/actionGetOrdersByTourId',
      actionGetGuideSelectBoxList: 'guide/actionGetGuideSelectBoxList',
    }),
    closeDialogAdd(e) {
      this.dialogAddVisible = false
      console.log(e)
    },
    closeDialogUpdate(e) {
      this.dialogUpdateVisible = false
      console.log(e)
    },
    handleCurrentChange(val) {
      this.currentPage2 = val
    },
    handleSizeChange(val) {
      this.pageSize2 = val
    },
    getFormattedDate(date) {
      let year = date.getFullYear()
      let month = (1 + date.getMonth()).toString().padStart(2, '0')
      let day = date.getDate().toString().padStart(2, '0')

      return month + '/' + day + '/' + year
    },
    async getData() {
      await this.actionTourList(this.objectSearch)
      this.list = this.tourListGet
    },
    getFormattedDateForSchedule(date) {
      let year = date.getFullYear()
      let month = (1 + date.getMonth()).toString().padStart(2, '0')
      let day = date.getDate().toString().padStart(2, '0')

      return year + '-' + month + '-' + day
    },
    async getTourScheduleById(id) {
      await this.actionGetTourScheduleById(id)
      this.dataSchedule = this.getTourScheduleByTourId
      for (let i = 0; i < this.dataSchedule.length; i++) {
        this.dataSchedule[i].time = this.getFormattedDateForSchedule(
          new Date(this.dataSchedule[i].time),
        )
      }
      console.log(this.dataSchedule)
    },
    handleDetailClick(data) {
      // this.loading = ref(true)
      this.dialogDetailVisible = true
      this.dataTour = Object.assign({}, data)
      this.dataTour.startTime = this.getFormattedDateForSchedule(
        new Date(this.dataTour.startTime),
      )
      this.actionTourPriceById(data.id)
      this.actionLandTourPriceById(data.id)
      this.actionGetOrdersByTourId(data.id)
      this.getTourScheduleById(data.id)
      this.getDiscountTourRit(data.id)
    },
    async getDiscountTourRit(id) {
      await this.actionGetDiscountTour(id)
      this.discountTour = this.getDiscountTour

      if (this.discountTour.startDate != null) {
        this.discountTour.startDate = this.getFormattedDateForSchedule(
          new Date(this.discountTour.startDate),
        )
        console.log('abccc' + this.discountTour.startDate + '--1')
      }
      if (this.discountTour.endDate != null) {
        this.discountTour.endDate = this.getFormattedDateForSchedule(
          new Date(this.discountTour.endDate),
        )
        console.log('abccc' + this.discountTour.endDate + '--2')
      }
    },
    handleSearchClick() {
      this.getData()
    },
    handleUpdateClick(data) {
      this.dialogUpdateVisible = true
      this.dataTour = Object.assign({}, data)
      this.dataTour.startTime = this.getFormattedDateForSchedule(
        new Date(this.dataTour.startTime),
      )
      this.actionTourPriceById(data.id)
      this.actionLandTourPriceById(data.id)
      this.actionGetOrdersByTourId(data.id)
      this.getTourScheduleById(data.id)
      this.getDiscountTourRit(data.id)
    },
    handleAddClick() {
      this.dialogAddVisible = true
    },
  },
}
</script>
