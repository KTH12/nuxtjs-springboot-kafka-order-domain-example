<template>
  <div class="container">
    <div class="wrap">
      <div class="grid">
        <template v-for="product in productList">
          <vs-row align="center" direction="column">
            <vs-col class="stor-card" w="6">
              <div class="product-info">
                {{ product.name }} : {{ product.price }}원
              </div>
              <div class="card-right">
                <vs-switch @click="productSelected(product.id)">
                  <template #off>
                    주문선택
                  </template>
                  <template #on>
                    주문해제
                  </template>
                </vs-switch>
              </div>
            </vs-col>
          </vs-row>
        </template>
        <vs-row align="center" direction="column">
          <vs-col w="6">
            <vs-button
                size="xl"
                color="#7d33ff"
                gradient
                block
                @click="order"
            >
              주문하기
            </vs-button>
          </vs-col>
        </vs-row>
      </div>
    </div>
    <vs-dialog overflow-hidden v-model="dialogActive">
      <template #header>
        <h3>
          주문 접수 완료
        </h3>
      </template>
    </vs-dialog>
  </div>
</template>

<script>
export default {
  name: "list.vue",
  data() {
    return {
      dialogActive: false,
      selected: [],
      productList: [],
      storeId: null,
    }
  },
  async asyncData({params}) {
    const storeId = params.storeId
    return {storeId}
  },
  beforeMount() {
    this.getProductList();
  },
  methods: {
    order() {
      if (this.selected.length === 0) {
        alert('주문하실 상품을 선택해주세요.')
        return false;
      }
      this.postOrder(this.selected)
      this.dialogActive = true
    },
    orderConfirm() {
      this.dialogActive = false
      this.postOrder(this.selected);
    },
    getProductList() {
      this.$axios({
        method: 'GET',
        url: 'http://localhost:8089/v1/1/products',
      })
          .then(res => {
            this.productList = res.data
            console.log(this.productList)
          }).catch(e => {
        console.log(e)
      })
    },
    postOrder(selectList) {
      selectList.forEach(data => {
        this.$axios({
          method: 'POST',
          url: 'http://localhost:8089/v1/topic/order',
          data: {
            'productId': data,
            'qty': 1
          }
        })
            .then(res => {
              console.log(res)
            }).catch(e => {
          console.log(e)
        })
      })
    },
    productSelected(productId) {
      this.selected.push(productId)
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

  .stor-card {
    height: 3rem;
    line-height: 3rem;
    background-color: #EDB7B7;
    border-radius: 0.5rem;
    padding: 0 1rem;
    margin-bottom: 1rem;

    .card-right {
      position: absolute;
      right: 1rem;
      top: 20%;
    }
  }
}
</style>
