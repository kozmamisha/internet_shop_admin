<#import "parts/templadmin.ftl" as c>
<@c.pages>
<h1>Form Order</h1>

<h2> Order </h2>

<table class="table">
    <thead>
    <tr>
        <th>image</th>
        <th>name</th>
        <th>quan</th>
        <th>price</th>
        <th>value</th>
    </tr>
    </thead>

    <tbody>
    <#if itemCart??>
        <#list itemCart as item>
            <tr>
                <td><img src="${item.product.image}" alt="${item.product.name}" height="40px"></td>
                <td>${item.product.name}</td>
                <td>${item.quantity}</td>
                <td>${item.product.price}</td>
                <td>${item.product.price*item.quantity}</td>
            </tr>
        </#list>
    </#if>
    </tbody>
</table>

<p> Total value: <a> ${totalValue} </a></p>
<p> Sul el: <a> ${totalEl} </a></p>

<h2>Client</h2>

<table class="table">
    <thead>
    <tr>
        <th>firstName</th>
        <th>lastName</th>
        <th>age</th>
        <th>phone</th>
        <th>email</th>
        <th>payment</th>
        <th>delivery</th>
    </tr>
    </thead>
    <tbody>

    <tr>
        <td>${client.firstName}</td>
        <td>${client.lastName}</td>
        <td>${client.age}</td>
        <td>${client.phone}</td>
        <td>${client.email}</td>



        <form action="/order" method="post">
            <td>
                <select name="payment">
                    <option value="cash" selected>Cash</option>
                    <option value="card">Card</option>
                </select>
            </td>

            <td>
                <select name="delivery">
                    <option value="nova_poshta" selected>Nova Poshta</option>
                    <option deliverer="">Deliverer</option>
                </select>
            </td>
    </tr>
    </tbody>

</table>


<h2>Address</h2>
<select name="address">

    <#list addresses as address>
        <option value="${address.id}">${address.city +", "+ address.country + ", " + address.building} </option>
    </#list>
</select>

<h2></h2>

<button type="submit">Add to DB</button>
</form>

</@c.pages>
