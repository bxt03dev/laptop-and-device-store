<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">


    <!-- Icon Font Stylesheet -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

    <!-- Libraries Stylesheet -->
    <link href="/client/lib/lightbox/css/lightbox.min.css" rel="stylesheet">
    <link href="/client/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">


    <!-- Customized Bootstrap Stylesheet -->
    <link href="/client/css/bootstrap.min.css" rel="stylesheet">
    <link href="/client/css/custom.css" rel="stylesheet">

    <!-- Template Stylesheet -->
    <link href="/client/css/style.css" rel="stylesheet">
</head>
<body>
    <div id="spinner" class="show w-100 vh-100 bg-white position-fixed translate-middle top-50">
        <div class="spinner-grow" role="status"></div>
    </div>
    <jsp:include page="../layout/header.jsp"/>
    <div class="container" style="margin-top: 100px;">
        <div class="row">
            <div class="col-12 mt-15">
                <div class="alert alert-success" role="alert">
                    Thank you for your order, the order has been successfully confirmed!
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="../layout/feature.jsp"/>
    <jsp:include page="../layout/footer.jsp"/>
</body>
</html>