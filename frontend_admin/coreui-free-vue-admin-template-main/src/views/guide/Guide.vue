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
        <el-form-item label="Tên">
          <el-input
            v-model="objectSearch.nameGuide"
            placeholder="Tìm kiếm theo tên"
          />
        </el-form-item>
      </CCol>
      <CCol>
        <el-form-item label="Số điện thoại">
          <el-input
            v-model="objectSearch.phoneGuide"
            placeholder="Tìm kiếm theo số điện thoại"
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
      :data="displayGuideData"
      stripe
      border
      style="width: 100%"
      v-loading="loading"
    >
      <el-table-column type="index" fixed />
      <el-table-column prop="id" label="Id" />
      <el-table-column prop="name" label="Họ và tên" />
      <el-table-column prop="phone" label="Số điện thoại" />
      <el-table-column prop="address" label="Địa chỉ" />
      <el-table-column fixed="right" label="Thao tác">
        <template #default="scope">
          <el-button
            link
            type="primary"
            size="small"
            @click="handleUpdateClick(scope.row)"
            >Chỉnh sửa</el-button
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
      :total="displayGuideData.length"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      style="float: right"
    />
    <el-dialog
      v-model="dialogUpdateVisible"
      title="Cập nhật thông tin hướng dẫn viên"
    >
      <UpdateGuide
        :dataGuide="guideUpdate"
        @close-dialog="closeDialog"
      ></UpdateGuide>
    </el-dialog>
    <el-dialog v-model="dialogAddVisible" title="Thêm mới hướng dẫn viên">
      <AddGuide :dataGuide="guideAdd" @close-dialog="closeDialogAdd"></AddGuide>
    </el-dialog>
  </div>
</template>
<script>
import { mapGetters, mapActions } from 'vuex'
import AddGuide from './AddGuide.vue'
import UpdateGuide from './UpdateGuide.vue'
export default {
  components: { AddGuide, UpdateGuide },
  name: 'Quản lý hướng dẫn viên',
  data() {
    return {
      currentPage2: 1,
      pageSize2: 10,
      dialogUpdateVisible: false,
      dialogAddVisible: false,
      objectSearch: {
        nameGuide: null,
        phoneGuide: null,
      },
      guideUpdate: {},
      guideAdd: {
        id: null,
      },
    }
  },
  computed: {
    ...mapGetters({
      getGuideList: 'guide/getGuideList',
      getMaxId: 'guide/getMaxId',
      authentication: 'user/getAuthentication',
    }),
    displayGuideData() {
      if (!this.getGuideList || this.getGuideList.length === 0) return []
      return this.getGuideList.slice(
        this.pageSize2 * this.currentPage2 - this.pageSize2,
        this.pageSize2 * this.currentPage2,
      )
    },
  },
  created() {
    if (localStorage.getItem('username') !== null) {
      this.actionGuideList(this.objectSearch)
    } else {
      this.$router.push({ name: 'Login' })
    }
  },
  methods: {
    ...mapActions({
      actionGuideList: 'guide/actionGuideList',
      actionGetMaxId: 'guide/actionGetMaxId',
    }),
    async handleAddClick() {
      this.dialogAddVisible = true
      await this.actionGetMaxId()
      this.guideAdd.id = this.getMaxId + 1
    },
    handleSearchClick() {
      this.actionGuideList(this.objectSearch)
    },
    handleUpdateClick(data) {
      this.dialogUpdateVisible = true
      this.guideUpdate = data
      console.log(data)
    },
    handleCurrentChange(val) {
      this.currentPage2 = val
    },
    handleSizeChange(val) {
      this.pageSize2 = val
    },
    closeDialog(e) {
      this.dialogUpdateVisible = false
      console.log(e)
    },
    closeDialogAdd(e) {
      this.dialogAddVisible = false
      console.log(e)
    },
  },
}
</script>
