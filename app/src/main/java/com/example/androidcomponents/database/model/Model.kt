package com.example.androidcomponents.database.model

class Model {
    data class EmployeeData(
        var id: Int,
        var employee_name: String,
        var employee_salary: Int,
        var employee_age: Int,
        var profile_image: String,
        var expand: Boolean = false
    )
}