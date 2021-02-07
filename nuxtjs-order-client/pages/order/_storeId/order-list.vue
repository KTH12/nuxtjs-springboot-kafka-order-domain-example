<template>
  <div class="container">
    <div class="wrap">
      <div class="grid">
        <vs-row align="center" justify="center" w="12">
          <vs-col class="order-list" vs-type="flex" vs-justify="center" vs-align="center" w="8">
            <div>
              <vs-table v-model="selected">
                <template #thead>
                  <vs-tr>
                    <vs-th>
                      주문상품
                    </vs-th>
                    <vs-th>
                      수량
                    </vs-th>
                    <vs-th>
                      주문확인
                    </vs-th>
                  </vs-tr>
                </template>
                <template #tbody>
                  <vs-tr
                      :key="i"
                      v-for="(tr, i) in orders"
                      :data="tr"
                      :is-selected="selected == tr"
                  >
                    <vs-td>
                      {{ tr.productName }}
                    </vs-td>
                    <vs-td>
                      {{ tr.qty }}
                    </vs-td>
                    <vs-td>
                      <vs-button
                          size="small"
                          :disabled="tr.isConfirm"
                          @click="confirm(tr.orderId)"
                      >
                        주문확인
                      </vs-button>
                    </vs-td>
                  </vs-tr>
                </template>
              </vs-table>
            </div>
          </vs-col>
        </vs-row>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "order-list",
  data() {
    return {
      selected: null,
      orders: [],
    }
  },
  mounted() {
    this.getOrderList()
  },
  methods: {
    getOrderList() {
      this.$axios({
        method: 'GET',
        url: 'http://localhost:8089/v1/1/orders',
      })
          .then(res => {
            this.orders = res.data
          }).catch(e => {
        alert('서버에러')
        console.log(e)
      })
    },
    confirm(orderId) {
      this.$axios({
        method: 'PUT',
        url: `http://localhost:8089/v1/order/${orderId}/confirm`,
      })
          .then(res => {
            this.orders = this.orders.map((v) => {
              if (v.orderId === orderId) {
                v.isConfirm = true;
              }
              return v;
            });
            console.log(res);
            this.openNotification('bottom-center')
          }).catch(e => {
        alert('서버에러')
      })
    },
    openNotification(position = null) {
      this.$vs.notification({
        color: 'dark',
        position,
        title: '알림',
        text: `주문확인 완료`
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.wrap {
  width: 100%;
  padding: 120px 0;

  & > .grid {
    min-height: 100%;

    & > .vs-row {
    }
  }

  .order-list {
    background-color: bisque;
  }
}
</style>