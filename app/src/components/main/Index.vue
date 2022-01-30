<template>
  <div>
  <Tags/>
  <Search />
  </div>
</template>

<script>
import Tags from '@/components/common/Tags';
import Search from '@/components/common/Search';

export default {
    name: 'Index',
    components: {Tags, Search},
    data() {
    return {};
    },
    methods: {}
}
</script>

<style scoped>

</style>
