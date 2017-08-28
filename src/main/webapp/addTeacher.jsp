<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Teacher</title>
        <link rel="stylesheet" type="text/css" href="static/css/addTeacher.css">
    </head>
    <body>
        <div class="main-container">
            <div class="contact-form" role="form">
                <header>
                    <h2>Add Teacher</h2>
                </header>
                <form method="post">
                    <div class="flex-container">
                        <label class="label-col">Name: <input type="text" name="name" class="field"></label>
                    </div>
                    <div class="flex-container">
                        <label class="label-col">Credits: <input type="text" name="designation" class="field"/></label>
                    </div>
                    <button>Add</button>
                </form>
            </div>
        </div>
    </body>
</html>
