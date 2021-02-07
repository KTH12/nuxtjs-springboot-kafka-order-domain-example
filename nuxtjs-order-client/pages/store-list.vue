<template>
  <div class="container">
    <div class="wrap">
      <div class="grid">
        <vs-row class="mh" align="center" direction="column">
          <vs-col w="10">
            <vs-row direction="column">
              <template v-for="storeItem in storeList">
                <vs-col class="store-card" w="12">
                  <span class="store-name">{{ storeItem.name }}</span>
                  <div class="store-image" :style="{
                      'background-image': 'url(' + require(`~/assets/images/${storeItem.imageUrl}`) + ')'
                  }"></div>
                  <div class="background-gradient" @click="goStore(storeItem.id)"></div>
                </vs-col>
              </template>
            </vs-row>
          </vs-col>
        </vs-row>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "store-list",
  data() {
    return {
      storeList: []
    }
  },
  mounted() {
    this.getStoreList();
  },
  methods: {
    getStoreList() {
      this.$axios({
        method: 'GET',
        url: 'http://localhost:8089/v1/stores',
      })
          .then(res => {
            this.storeList = res.data.map((v) => {
              v.imageUrl = `store${v.id}.jpg`
              return v
            })
          }).catch(e => {
        console.log(e)
      })
    },
    goStore(storeId) {
      location.href = `product/${storeId}/list`;
    },
  }
}
</script>

<style lang="scss" scoped>
.wrap {
  width: 100%;
  min-height: 30rem;
  padding: 120px 0;

  & > .grid {
    min-height: 100%;

    & > .vs-row {
      min-height: 30rem;
    }
  }

  .store-card {
    cursor: pointer;
    height: 8rem;
    line-height: 3rem;
    background-color: #EDB7B7;
    border-radius: 0.5rem;
    padding: 0 1rem;
    margin-bottom: 1rem;
    overflow: hidden;

    .store-name {
      position: relative;
      z-index: 1;
      left: 0.25rem;
      top: 0.5rem;
      font-size: 2rem;
    }

    .card-right {
      position: absolute;
      right: 0;
      top: 20%;
    }

    .store-image {
      background-size: cover;
      position: absolute;
      background-position: 0% 50%;
      left: 0;
      top: 0;
      right: 0;
      bottom: 0;
    }

    .background-gradient {
      position: absolute;
      top: 0;
      right: 0;
      left: 0;
      bottom: 0;
      background: linear-gradient(90deg, rgba(255, 255, 255, 0.7) 0%, rgba(234, 234, 234, 0.7) 20.04%, rgba(129, 129, 129, 0.3428) 72.45%, rgba(0, 0, 0, 0.07) 84.78%, rgba(0, 0, 0, 0.07) 98.66%);
    }
  }
}
</style>
