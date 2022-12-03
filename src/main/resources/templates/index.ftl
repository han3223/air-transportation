<html>
<body>
<h1>Items:</h1>
<select name="" id="">
    <#list data.items as item>
        <option value="ghbdnt"><h2>The item at index ${item?index} is ${item}</h2></option>
    </#list>
</select>
</body>
</html>
