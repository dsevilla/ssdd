from flask_login import UserMixin
import hashlib

users: list["User"] = []

class User(UserMixin):

    def __init__(self, id: int, name: str, email: str, password: bytes, is_admin: bool = False):
        self.id: int = id
        self.name: str = name
        self.email: str = email
        self.password: str = hashlib.sha256(password).hexdigest()
        self.is_admin: bool = is_admin

    def set_password(self, password: bytes):
        self.password: str = hashlib.sha256(password).hexdigest()

    def check_password(self, password: bytes):
        return self.password == hashlib.sha256(password).hexdigest()

    @staticmethod
    def get_user(email: str):
        for user in users:
            if user.email == email:
                return user
            return None

    def __repr__(self):
        return '<User {}>'.format(self.email)
