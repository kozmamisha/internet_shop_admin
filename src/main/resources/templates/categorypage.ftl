<#import "parts/templadmin.ftl" as c>
<@c.pages>

    <h2>Category</h2>
    <#import "parts/templadmin.ftl" as p>

    <@p.pager url page />

    <div class="row row-cols-2 row-cols-md-2 g-4">

        <#if page.content??>
            <#list page.content as category>
                <div class="col">
                    <div class="card">
                        <a href="/productbycategory/${category.id}">
                            <img src="${category.image}" class="card-img-top" alt="${category.name}">
                        </a>
                        <div class="card-body">
                            <h5 class="card-title">${category.name}</h5>
                            <p class="card-text">${category.description}</p>
                        </div>
                    </div>
                </div>


            </#list>
        </#if>

    </div>
    <p></p>
    <@p.pager url page />


</@c.pages>