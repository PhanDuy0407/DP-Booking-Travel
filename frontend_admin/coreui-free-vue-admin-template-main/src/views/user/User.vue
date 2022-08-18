<template>
  <div>
    <el-form label-width="160px">
      <CRow>
        <CCol>
          <el-form-item label="Id">
            <el-input type="number" v-model="objectSearch.id" />
          </el-form-item>
        </CCol>
        <CCol>
          <el-form-item label="Tên đăng nhập">
            <el-input v-model="objectSearch.username" />
          </el-form-item>
        </CCol>
      </CRow>
      <CRow>
        <CCol>
          <el-form-item label="Cấp độ người dùng">
            <el-select v-model="objectSearch.rank" placeholder="Chọn cấp độ">
              <el-option
                v-for="item in optionsRank"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              >
              </el-option>
            </el-select>
          </el-form-item>
        </CCol>
        <CCol>
          <el-form-item label="Trạng thái">
            <el-select
              v-model="objectSearch.status"
              placeholder="Chọn trạng thái"
            >
              <el-option
                v-for="item in optionsStatus"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              >
              </el-option>
            </el-select>
          </el-form-item>
        </CCol>
      </CRow>
      <CRow style="margin-bottom: 20px">
        <CCol style="text-align: center">
          <el-button type="primary" @click="onClickSearch">Tìm kiếm</el-button>
        </CCol>
      </CRow>
    </el-form>

    <el-table :data="displayUserData" style="width: 100%" ref="tableData">
      <el-table-column fixed prop="id" label="Id" width="50" />
      <el-table-column prop="username" label="Tên đăng nhập" width="120" />
      <el-table-column prop="fullname" label="Họ và tên" width="120" />
      <el-table-column prop="email" label="Email" width="120" />
      <el-table-column prop="address" label="Địa chỉ" width="120" />
      <el-table-column prop="dob" label="Ngày sinh" width="120" />
      <el-table-column prop="gender" label="Giới tính" width="120" />
      <el-table-column prop="password" label="Mật khẩu" width="120" />
      <el-table-column prop="createdDate" label="Ngày tạo" width="120" />
      <el-table-column prop="status" label="Trạng thái" width="120" />
      <el-table-column prop="rank" label="Cấp độ" width="120" />
      <el-table-column prop="totalOrder" label="Tổng đơn đặt" width="120" />
      <el-table-column fixed="right" label="Thao tác" width="150">
        <template #default="scope">
          <el-button
            v-if="stopActive[scope.row.id]"
            link
            type="primary"
            @click="handleStopActiveClick(scope.row)"
            >Dừng hoạt động</el-button
          >
          <el-button
            v-else
            link
            type="primary"
            @click="handleActiveClick(scope.row)"
            >Hoạt động</el-button
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
      :total="displayUserData.length"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      style="float: right"
    />
  </div>
</template>
<script>
import { mapGetters, mapActions } from 'vuex'
export default {
  name: 'Quản lý người dùng',
  data() {
    return {
      stopActive: [],
      currentPage2: 1,
      pageSize2: 10,
      dialogUpdateVisible: false,
      dialogAddVisible: false,
      objectSearch: {
        id: null,
        username: null,
        rank: null,
        status: null,
      },
      userStatus: {
        id: null,
        status: null,
      },
      optionsRank: [
        {
          value: 'normal',
          label: 'Bình thường',
        },
        {
          value: 'vip',
          label: 'Vip',
        },
      ],
      optionsStatus: [
        {
          value: '0',
          label: 'Không hoạt động',
        },
        {
          value: '1',
          label: 'Hoạt động',
        },
      ],
    }
  },
  computed: {
    ...mapGetters({
      getUserList: 'user/getUserList',
      authentication: 'user/getAuthentication',
    }),
    displayUserData() {
      if (!this.getUserList || this.getUserList.length === 0) return []
      return this.getUserList.slice(
        this.pageSize2 * this.currentPage2 - this.pageSize2,
        this.pageSize2 * this.currentPage2,
      )
    },
  },
  watch: {
    getUserList: {
      immediate: true,
      deep: true,
      handler: function () {
        for (let i = 0; i < this.getUserList.length; i++) {
          if (this.getUserList[i].status == 1) {
            this.stopActive[this.getUserList[i].id] = true
          } else {
            this.stopActive[this.getUserList[i].id] = false
          }
        }
      },
    },
  },
  created() {
    if (localStorage.getItem('username') === null) {
      this.$router.push({ name: 'Login' })
    } else {
      this.actionUserList(this.objectSearch)
      for (let i = 0; i < this.getUserList.length; i++) {
        if (this.getUserList[i].status == 1) {
          this.stopActive[this.getUserList[i].id] = true
        } else {
          this.stopActive[this.getUserList[i].id] = false
        }
      }
      var me = this
      this.timer = setInterval(function () {
        me.actionUserList(me.objectSearch)
      }, 5000)
    }
  },
  methods: {
    ...mapActions({
      actionUserList: 'user/actionUserList',
      actionUserStatus: 'user/actionUserStatus',
    }),
    onClickSearch() {
      console.log(this.objectSearch)
      this.actionUserList(this.objectSearch)
    },
    async handleStopActiveClick(data) {
      this.userStatus.id = data.id
      this.userStatus.status = 0
      this.stopActive[data.id] = false
      await this.actionUserStatus(this.userStatus)
    },
    async handleActiveClick(data) {
      this.userStatus.id = data.id
      this.userStatus.status = 1
      this.stopActive[data.id] = true
      await this.actionUserStatus(this.userStatus)
    },

    handleUpdateClick(data) {
      this.dialogUpdateVisible = true
      this.guideUpdate = data
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
    cancelAutoUpdate() {
      clearInterval(this.timer)
    },
  },
  beforeUnmount() {
    this.cancelAutoUpdate()
  },
}
</script>
