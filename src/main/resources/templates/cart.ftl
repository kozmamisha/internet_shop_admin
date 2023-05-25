<#import "parts/templadmin.ftl" as c>
<@c.pages>
    <h2> Cart </h2>

    <table class="table">
        <thead>
        <tr>
            <th>image</th>
            <th>name</th>
            <#--            <th>desc</th>-->
            <th>quantity</th>
            <th>price</th>
            <th>value</th>
            <th>update</th>
            <th>delete</th>
        </tr>
        </thead>

        <tbody>
        <#if itemCart??>
            <#list itemCart as item>

                <form action="/updateItemCart" method="post">
                    <input type="hidden" name="id" value="${item.product.id}">
                    <tr>
                        <td><img src="${item.product.image}" alt="${item.product.name}" height="40px"></td>
                        <td>${item.product.name}</td>
                        <#--            <td>${item.product.description}</td>-->
                        <td>
                            <div>
                                <button class="minus btn btn-success"> - </button>
                                <input type="text" id="quantity" name="quantity" value="${item.quantity}" size="3">
                                <button class="plus btn btn-success"> + </button>
                            </div>
                        </td>
                        <td>${item.product.price}</td>
                        <td>${item.product.price*item.quantity}</td>
                        <td>
                            <button type="submit" class="btn btn-primary">update</button>
                        </td>
                </form>
                <form action="/deleteItemCart" method="post">
                    <td>
                        <input type="hidden" name="id" value="${item.product.id}">
                        <input type="submit" class="btn btn-primary" value="delete">
                    </td>
                </form>
                </tr>

            </#list>
        </#if>
        </tbody>
    </table>

    <p> Total value: <a> ${totalValue} </a></p>
    <p> Total elements: <a> ${totalEl} </a></p>

    <form action="/deleteItemsCart" method="post">
        <button type="submit"> Delete all items from cart </button>
    </form>


    <p></p>
    <h2>Order</h2>
    <form action="/thank" method="get">
        <button type="submit"> Go to order </button>
    </form>

    <p> </p>
</@c.pages>