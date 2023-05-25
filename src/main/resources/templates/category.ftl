<#import "parts/templadmin.ftl" as p>
<@p.pages>

    <h2>Categories</h2>

    <div class="row">

        <#if allCategory??>
            <#list allCategory as category>
                <div class="col-lg-3 col-md-6 col-sm-6 col-12 g-4">
                    <div class="card">
                        <a href="/category/${category.id}">
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
</@p.pages>