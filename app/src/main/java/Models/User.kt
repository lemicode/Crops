package Models

class User {

    companion object {

        private var email: String = ""

        fun setEmail(value: String) {
            email = value
        }

        fun getEmail() = email

    }

}