A basic backend for a personal webapp project.

Features:
Fetching and posting Tasks.

Task body:
{
  id: Long,
  title: String,
  description: String,
  completed: boolean
}

Api endpoints:

Get all tasks:
GET /api/tasks

Get single task:
Get /api/tasks/{id}

Post a task:
POST /api/tasks

Delete a task:
DELETE /api/tasks/{id}

Update a task:
PUT /api/tasks/{id}

Coming soon:
Users
Authentication(login)
Posts
