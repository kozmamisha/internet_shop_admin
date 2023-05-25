<#import "parts/templadmin.ftl" as p>
<@p.pages>

    <#if category??>
        <h1> Category </h1>

        <p></p>
        <p>id : ${category.id}</p>
        <p>name : ${category.name}</p>
        <p>description : ${category.description}</p>
        <p>image : ${category.image}</p>
    </#if>

</@p.pages>