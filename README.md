<img src="https://user-images.githubusercontent.com/238198/177164121-3aa4d19d-7714-4f2e-af12-7d3335b43f9c.png" width="75" />

# Appyx codelabs

This respository contains codelabs to learn about Appyx. You will also find one project for each codelab so you can easily follow them.


## Appyx

Don't know yet what Appyx is?

Find out by checking the oficial doc:
https://bumble-tech.github.io/appyx/


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

## License

<pre>
Copyright 2021 Bumble.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
</pre>
