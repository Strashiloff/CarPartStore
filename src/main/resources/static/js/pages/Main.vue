<template>
    <div xmlns="http://www.w3.org/1999/xhtml" xmlns:th="https://www.thymeleaf.org"
         xmlns:sec="https://www.thymeleaf.org/thymeleaf-extras-springsecurity3">
        <div th:if="${param.error}">
            Invalid username and password.
        </div>
        <div v-if="profile==''">Необходимо авторизоваться через
                <a href="/login">Google</a>
        </div>
        <div v-else>
            <div>[[${#httpServletRequest.remoteUser}]]&nbsp;
                <form th:action="@{/logout}" method="post">
                    <input type="submit" value="Sign Out"/>
                </form>
            </div>
        </div>
        <router-view></router-view>
    </div>
</template>

<script>
    export default {
        name: 'Main',
        data: function () {
            return{
                profile: frontendData
            }
        },
        mounted:function () {
            console.log(this.profile)
        }
    }
</script>

<style>

</style>