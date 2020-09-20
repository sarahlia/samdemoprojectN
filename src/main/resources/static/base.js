(function($) {
    let request = $.ajax({'url': 'products.json'});
    request.done(function (products) {
        // console.log(products);
        let html = '';
        products.forEach(function(product) {
            html += `<div>
                        <h2>${product.name}</h2>
                        <p>${product.description}</p>
                        <p>${product.price}</p>
                        <img src="${product.img}" alt="image">
                     </div>`
        });
        $('#products').html(html);
    });
})(jQuery);