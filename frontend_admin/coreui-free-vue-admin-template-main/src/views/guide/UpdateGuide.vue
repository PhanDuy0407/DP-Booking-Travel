<template>
  <el-form label-width="160px">
    <CRow>
      <CCol>
        <el-form-item label="Id">
          <el-input type="number" v-model="guideUpdate.id" disabled />
        </el-form-item>
      </CCol>
    </CRow>
    <CRow>
      <CCol>
        <el-form-item label="Họ và tên">
          <el-input v-model="guideUpdate.name" />
        </el-form-item>
      </CCol>
    </CRow>
    <CRow>
      <CCol>
        <el-form-item label="Số điện thoại">
          <el-input v-model="guideUpdate.phone" />
        </el-form-item>
      </CCol>
    </CRow>
    <CRow>
      <CCol>
        <el-form-item label="Địa chỉ">
          <el-input v-model="guideUpdate.address" />
        </el-form-item>
      </CCol>
    </CRow>
    <CRow style="margin-top: 20px">
      <CCol>
        <div style="float: right">
          <el-button type="danger" @click="closeDialogLog">Đóng</el-button>
          <el-button type="success" @click="onClickUpdate">Lưu</el-button>
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
        console.log(this.guideUpdate)
        this.guideUpdate = this.dataGuide
      },
    },
  },

  data() {
    return {
      guideUpdate: {
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
    ...mapGetters({}),
  },
  created() {},

  methods: {
    ...mapActions({
      actionPostUpdateGuide: 'guide/actionPostUpdateGuide',
      actionGuideList: 'guide/actionGuideList',
    }),
    async onClickUpdate() {
      var str = 'Chưa điền '
      if (this.guideUpdate.name == null || this.guideUpdate.name == '') {
        str += 'tên,'
      }
      if (this.guideUpdate.phone == null || this.guideUpdate.phone == '') {
        str += 'số điện thoại,'
      }
      if (this.guideUpdate.address == null || this.guideUpdate.address == '') {
        str += 'địa chỉ,'
      }
      if (str != 'Chưa điền ') {
        this.$notify({
          title: 'Cảnh báo',
          message: str,
          type: 'warning',
        })
      } else {
        await this.actionPostUpdateGuide(this.guideUpdate)
        await this.actionGuideList(this.objectSearch)
        this.closeDialogLog()
        this.$notify({
          title: 'Thành công',
          message: 'Cập nhật thành công',
          type: 'success',
        })
      }
    },
    closeDialogLog() {
      console.log('click')
      this.$emit('close-dialog', 'false')
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
