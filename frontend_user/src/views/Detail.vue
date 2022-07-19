<template>
  <div>
    <v-card color="#f9f9f9">
      <v-card-title class="d-flex justify-space-between">
        <div class="text-h5 font-weight-bold">{{ tour.name }}</div>
        <div class="d-flex flex-column">
          <v-btn color="error" width="160px" :to="{ name: 'Order', params: { id: tour.id }}">Đặt ngay</v-btn>
          <v-btn class="mt-4" width="160px">Liên hệ tư vấn</v-btn>
        </div>
      </v-card-title>
      <v-card-text>
        <v-row>
          <v-col>
            <v-img
              :src="tour.mainImageUrl"
              :aspect-ratio="16 / 9"
              gradient="to top, rgba(25,32,72,.4), rgba(25,32,72,.0)"
              style="border-radius: 16px"
              height="400px"
            ></v-img>
          </v-col>
          <v-col>
            <v-card height="400px" class="overflow-auto">
              <v-card-text class="black--text">
                {{ tour.shortDesc }}
              </v-card-text>
            </v-card>
          </v-col>
        </v-row>
        <v-row>
          <v-col>
            <v-card height="300px">
              <v-card-text class="black--text">
                <p>
                  Khởi hành:
                  <span class="font-weight-bold">{{ formatDate(tour.startTime) }}</span>
                </p>
                <p>
                  Thời gian:
                  <span class="font-weight-bold">{{ tour.period }} ngày</span>
                </p>
                <p>
                  Nơi khởi hành:
                  <span class="font-weight-bold">{{
                    tour.startPlaceName
                  }}</span>
                </p>
                <p>
                  Số chỗ còn nhận:
                  <span class="font-weight-bold">{{ tour.placeOrderMax }}</span>
                </p>
              </v-card-text>
            </v-card>
          </v-col>
          <v-col>
            <v-card height="300px">
              <v-card-title>Địa điểm tham quan</v-card-title>
              <v-card-text>
                <li v-for="(item, index) in locations" :key="index">
                  {{ item }}
                </li>
              </v-card-text>
            </v-card>
          </v-col>
        </v-row>
      </v-card-text>
    </v-card>

    <h2 class="mt-16 text-center">Lịch trình</h2>
    <v-card>
      <v-row>
        <v-col lg="4" style="background-color: #f9f9f9">
          <v-card-text
            class="mb-10"
            v-for="(item, index) in tour.tourScheduleList"
            :key="index"
          >
            <div class="d-flex justify-space-between">
              <div>{{ item.alias }}</div>
              <div>{{ formatDate(item.time) }}</div>
            </div>
            <div>{{ item.location }}</div>
          </v-card-text>
        </v-col>
        <v-col>
          <v-card-text v-for="(item, index) in tour.tourScheduleList" :key="index">
            <h4>
              {{item.location}}
            </h4>
            <li v-for="(item2, index2) in splitText(item.detail)" :key="index2">
              {{item2}}
            </li>
          </v-card-text>
        </v-col>
      </v-row>
    </v-card>

    <h2 class="mt-16 text-center">Thông tin tour</h2>

    <v-row>
      <v-col>
        <v-card>
          <v-card-title class="justify-center"
            >Thông tin tập trung</v-card-title
          >
          <v-card-text>
            <div>Ngày giờ tập trung:</div>
            <div>Nơi tập trung:</div>
          </v-card-text>
        </v-card>
        <v-card class="mt-4">
          <v-card-title class="justify-center"
            >Thông tin hướng dẫn viên</v-card-title
          >
          <v-card-text>
            <div>HDV dẫn đoàn: {{tour.guideName}}</div>
            <div>HDV tiễn: {{tour.guideName}}</div>
          </v-card-text>
        </v-card>
      </v-col>
      <v-col>
        <v-card>
          <v-card-title class="justify-center"
            >Giá tour và Phụ thu</v-card-title
          >
          <v-card-text>
            <v-simple-table>
              <thead>
                <tr class="font-weight-bold">
                  <th class="text-left">Loại khách</th>
                  <th class="text-center">Giá tour</th>
                  <th class="text-right">Land tour</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>Người lớn (Từ 12 tuổi trở lên)</td>
                  <td class="red--text text-right">{{formatCurrency(tour.tourPrice.adult)}}</td>
                  <td class="text-right">{{formatCurrency(tour.landTourPrice.adult)}}</td>
                </tr>
                <tr>
                  <td>Trẻ em (Từ 5 - 11 tuổi)</td>
                  <td class="red--text text-right">{{formatCurrency(tour.tourPrice.children)}} </td>
                  <td class="text-right">{{formatCurrency(tour.landTourPrice.children)}}</td>
                </tr>
                <tr>
                  <td>Trẻ nhỏ (Từ 2 - 4 tuổi)</td>
                  <td class="red--text text-right">{{formatCurrency(tour.tourPrice.kid)}} </td>
                  <td class="text-right">{{formatCurrency(tour.landTourPrice.kid)}}</td>
                </tr>
                <tr>
                  <td>Em bé (Dưới 2 tuổi)</td>
                  <td class="red--text text-right">{{formatCurrency(tour.tourPrice.baby)}} </td>
                  <td class="text-right">{{formatCurrency(tour.landTourPrice.baby)}} </td>
                </tr>
                <tr class="font-weight-bold">
                  <td class="font-weight-bold">Phụ thu phòng đơn</td>
                  <td class="red--text text-right">{{formatCurrency(tour.tourPrice.surcharge)}}</td>
                  <td class="text-right">{{formatCurrency(tour.landTourPrice.surcharge)}}</td>
                </tr>
              </tbody>
            </v-simple-table>
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>

    <!-- <v-row>
      <v-col cols="12" lg="12" xl="8">
        <div>
          <div>
            <v-card flat color="transparent">
              <v-img
                src="https://cdn.pixabay.com/photo/2020/02/07/20/32/cyprus-4828328_1280.jpg"
                :aspect-ratio="16 / 9"
                gradient="to top, rgba(25,32,72,.4), rgba(25,32,72,.0)"
                style="border-radius: 16px"
              ></v-img>

              <v-card-text>
                <div>
                  <v-btn color="accent">ANIMAL</v-btn>
                </div>

                <div class="text-h4 font-weight-bold primary--text pt-4">
                  <h4>15 Things | have always wondered about birds</h4>
                </div>

                <div class="text-body-1 py-4">
                  Mattis enim ut tellus elementum sagittis vitae et. Enim ut tellus elementum sagittis vitae et leo duis ut. Quis vel eros donec.
                  Faucibus nisl tincidunt eget nullam. Quis commodo odio aenean sed adipiscing diam doneg.
                </div>

                <div class="d-flex align-center justify-space-between">
                  <div class="d-flex align-center">
                    <v-avatar color="accent" size="36">
                      <v-icon dark>mdi-feather</v-icon>
                    </v-avatar>

                    <div class="pl-2 text-body-1">Yan Lee · 22 July 2019</div>
                  </div>

                  <div class="d-flex align-center">
                    <div>
                      <v-chip small color="transparent">
                        <v-icon left>mdi-eye</v-icon>1.4k
                      </v-chip>

                      <v-chip small color="transparent">
                        <v-icon left>mdi-comment-outline</v-icon>7 Comment
                      </v-chip>
                    </div>
                  </div>
                </div>

                <v-divider class="my-4"></v-divider>

                <div>
                  <p class="text-subtitle-1 primary--text font-weight-medium">
                    Dignissim cras tincidunt lobortis feugiat vivamus at. Amet luctus venenatis lectus magna fringilla. Nibh tellus molestie nunc non blandit.
                    Et magnis dis parturient montes nascetur ridiculus mus mauris vitae. Amet massa vitae tortor condimentum lacinia quis vel eros. Eros
                    in cursus turpis massa tincidunt dui ut ornare. Est ante in nibh mauris cursus mattis molestie. Nec ullamcorper sit amet risus nullam
                    eget felis eget. Tincidunt praesent semper feugiat nibh sed. Et leo duis ut diam quam nulla pottitor massa id. Convallis convallis tellus id
                    interdum velit laoreet id. Enim ut sem viverra aliquet eget sit. Mollis aliquam ut porttitor leo a diam. Eleifend donec pretium vulputate
                    sapien nec sagittis aliquam. Velit egestas dui id ornares.
                  </p>
                </div>

                <div class="py-4">
                  <v-alert
                    class="font-italic text-h6 text-center"
                    border="left"
                    colored-border
                    color="accent"
                  >
                    Srem Ipsum is simply dummy text of the printing and typesetting industry. Lorem lpsum has been
                    the industry's standard dummy text ever since the 1500s. Lorem Ipsum is simply .
                  </v-alert>
                </div>

                <div class="text-h5 primary--text font-weight-bold">
                  Ultricies mi quis hendrerit dolor
                  <p class="text-subtitle-1 primary--text font-weight-medium mt-5">
                    Quam adipiscing vitae proin sagittis nisl rhoncus. Integer vitae justo eget magna fermentum iaculis eu non. Vitae congue mauris
                    rhoncus aenean vel elit. Nibh mauris cursus mattis molestie. Etiam sit amet nisl purus. At auctor urna nunc id cursus metus. Diam in arcu
                    cursus euismod quis viverra nibh cras.
                  </p>
                </div>

                <div class="my-4">
                  <v-row>
                    <v-col cols="6">
                      <v-card>
                        <v-img
                          src="https://cdn.pixabay.com/photo/2015/03/26/09/47/sky-690293_1280.jpg"
                          :aspect-ratio="16 / 9"
                          gradient="to top, rgba(25,32,72,.4), rgba(25,32,72,.0)"
                        ></v-img>
                      </v-card>
                    </v-col>

                    <v-col cols="6">
                      <v-card>
                        <v-img
                          src="https://cdn.pixabay.com/photo/2019/11/01/11/08/landscape-4593909_1280.jpg"
                          :aspect-ratio="16 / 9"
                          gradient="to top, rgba(25,32,72,.4), rgba(25,32,72,.0)"
                        ></v-img>
                      </v-card>
                    </v-col>
                  </v-row>
                </div>

                <div class="text-h5 primary--text font-weight-bold">
                  Nibh tellus molestie nunc non blandit massa enim
                  <div class="text-subtitle-1 primary--text font-weight-medium mt-5">
                    <ul>
                      <li class="my-2">
                        Aliquam purus sit amet luctus venenatis lectus magna fringilla urna. Malesuada bibendum arcu vitae elementum curabitur vitae
                        nunc sed. Urna porttitor rhoncus dolor purus non enim praesent elementum facilisis.
                      </li>

                      <li class="my-2">
                        Proin libero nunc consequat interdum varius. Amet luctus venenatis lectus magna fringillal urna porttitor. Enim tortor at auctor
                        urna nunc id cursus.
                      </li>

                      <li class="my-2">
                        Volutpat maecenas volutpat blandit aliquam. Adipiscing enim eu turpis egestas pretium aenean pharetra magna Morbi leo
                        urna molestie at elementum eu.
                      </li>
                    </ul>

                    <p>
                      Venenatis a condimentum vitae sapien pellentesque habitant morbi tristique senectus. Semper auctor neque vitae tempus. Magnis dis
                      parturient montes nascetur ridiculus mus. A condimentum vitae sapien pellentesque habitant morbi tristique. Duis ut diam quam nulla
                      porttitor.
                    </p>
                  </div>
                </div>

                <div class="d-flex align-center justify-space-between mt-8">
                  <div>
                    <v-row>
                      <v-col class="flex-shrink-0" cols="auto">
                        <v-chip color="accent">#Animalis</v-chip>
                      </v-col>

                      <v-col class="flex-shrink-0" cols="auto">
                        <v-chip color="accent">#Travel</v-chip>
                      </v-col>

                      <v-col class="flex-shrink-0" cols="auto">
                        <v-chip color="accent">#Birds</v-chip>
                      </v-col>
                    </v-row>
                  </div>

                  <div class="text-h5">
                    Share >
                    <v-btn icon large>
                      <v-icon large color="primary">mdi-facebook</v-icon>
                    </v-btn>

                    <v-btn icon large>
                      <v-icon large color="primary">mdi-twitter</v-icon>
                    </v-btn>

                    <v-btn icon large>
                      <v-icon large color="primary">mdi-linkedin</v-icon>
                    </v-btn>

                    <v-btn icon large>
                      <v-icon large color="primary">mdi-instagram</v-icon>
                    </v-btn>
                  </div>
                </div>

                <v-divider class="my-8"></v-divider>

                <div class="my-8">
                  <div class="d-flex align-center">
                    <v-avatar color="accent" size="128">
                      <v-icon dark size="100">mdi-feather</v-icon>
                    </v-avatar>

                    <div class="pl-4">
                      <div class="text-h5 primary--text font-weight-bold">
                        Written by
                        <span class="accent--text">Yan Lee</span>
                      </div>

                      <div class="text-subtitle-1 my-2">
                        Congue mauris rhoncus aenean vel elit. Elit scelerisque mauris pellentesque pulvinar pellentesque habitant. Aliquet nec
                        ullamcorper sit amet risus nullam eget felis.
                      </div>

                      <div class="text-subtitle-1">
                        Get in touch >
                        <v-btn icon>
                          <v-icon>mdi-facebook</v-icon>
                        </v-btn>

                        <v-btn icon>
                          <v-icon>mdi-twitter</v-icon>
                        </v-btn>

                        <v-btn icon>
                          <v-icon>mdi-youtube</v-icon>
                        </v-btn>

                        <v-btn icon>
                          <v-icon>mdi-instagram</v-icon>
                        </v-btn>
                      </div>
                    </div>
                  </div>
                </div>

                <div>
                  <v-row justify="space-between">
                    <v-col cols="12" md="6" lg="4">
                      <div class="d-flex align-center">
                        <div>
                          <v-icon>mdi-arrow-left</v-icon>
                        </div>

                        <div class="text-h6 primary--text pl-2">
                          <div class="text-subtitle-1">Previous Post</div>Photos of Jeep models that will change
                          your mood
                        </div>
                      </div>
                    </v-col>

                    <v-col cols="12" md="6" lg="4">
                      <div class="d-flex align-center text-right">
                        <div class="text-h6 primary--text pr-2">
                          <div class="text-subtitle-1">Next Post</div>What do I need to know to start learning
                          JavaScript?
                        </div>

                        <div>
                          <v-icon>mdi-arrow-right</v-icon>
                        </div>
                      </div>
                    </v-col>
                  </v-row>
                </div>
              </v-card-text>
            </v-card>
          </div>
        </div>
      </v-col>

      <v-col>
        <div>
          <siderbar />
        </div>
      </v-col>
    </v-row> -->
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
export default {
  name: "Category",
  components: {
    siderbar: () => import("@/components/details/sidebar"),
  },
  mounted() {
    // const route = useRoute();
    // console.log("route", route.params.id)
    console.log(this.$route.params.id);
  },
  computed: {
    ...mapGetters({
      tour: "tourList/getTour",
    }),
  },
  data() {
    return {
      locations: ["HN", "TPHCM"],
    };
  },
  created() {
    this.getTour(this.$route.params.id);
  },
  methods: {
    ...mapActions({
      getTour: "tourList/getTour",
    }),
    splitText(para){
      if(para != null ){
        const a = para.split('\\n')
        console.log(a)
        return a
      }
      return []
    },
    formatDate(strDate){
      const date = new Date(strDate)
      const yyyy = date.getFullYear();
      let mm = date.getMonth() + 1; // Months start at 0!
      let dd = date.getDate();
      if (dd < 10) dd = "0" + dd;
      if (mm < 10) mm = "0" + mm;
      return dd + "-" + mm + "-" + yyyy;
    },
    formatCurrency(currency){
      return new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(currency)
    }
  },
};
</script>
