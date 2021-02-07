<template>
  <div class="container center">
    <div class="grid" style="width: 50rem">
      <vs-row>
        <vs-col offset="2" w="8">
          <vs-alert gradient >
            <template #title>
              미리 주문 예약하고 바로 픽업!
            </template>
            <p>주문하는 즉시 해당 매장에 전달되어 제조가 시작됩니다.</p>
            <p>미리 주문하고 바로 픽업해보세요!</p>
          </vs-alert>
        </vs-col>
      </vs-row>

      <vs-row class="m-top-5">
        <vs-col offset="2" w="8">
          <div class="center">
            <vs-button
              size="xl"
              color="#7d33ff"
              gradient
              block
              to="/store-list"
            >
              매장 보러가기
            </vs-button>
          </div>
        </vs-col>
      </vs-row>
    </div>
  </div>
</template>

<script>

export default {
  components: {
  }
}
</script>

<style>

</style>
