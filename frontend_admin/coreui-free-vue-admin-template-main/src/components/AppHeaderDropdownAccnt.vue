<template>
  <CDropdown variant="nav-item"></CDropdown>
</template>

<script>
import avatar from '@/assets/images/avatars/8.jpg'
export default {
  name: 'AppHeaderDropdownAccnt',
  setup() {
    return {
      avatar: avatar,
      itemsCount: 42,
    }
  },
}
</script>
