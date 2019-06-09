<template xmlns:th="http://www.w3.org/1999/xhtml">
    <div>
        <div th:if="${param.error}">
        Invalid username and password.
        </div>
        <div th:if="${param.logout}">
            You have been logged out.
        </div>
        <form th:action="@{/login}" method="post">
            <div><label> User Name : <input type="text" name="username"/> </label></div>
            <div><label> Password: <input type="password" name="password"/> </label></div>
            <div><input type="submit" value="Sign In"/></div>
        </form>
    </div>
</template>

<script>
    export default {
        name: 'Login',
    }
</script>

<style>

</style>