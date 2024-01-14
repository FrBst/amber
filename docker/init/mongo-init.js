db.createUser(
    {
      user: "amber",
      pwd:  "amber",
      roles: [ { role: "readWrite", db: "amber" } ]
    }
)
