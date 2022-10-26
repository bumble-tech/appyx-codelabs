## Building codelabs

Install `claat` following the tutorial [here](https://github.com/googlecodelabs/tools/tree/main/claat)

Navigate to:
```
/codelabs
    /tooling
```


Run the following script:
```
./codelab.py gen <project-id>
```
(Python3 is needed to run the script)

`project-id` is under the metadata in the .md file your building.

The project will be generated under:

```
/codelabs
    /html
        /<project-id>
            /index.html
```
