[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0) [![Clojars Project](https://img.shields.io/clojars/v/studiokalavati/sargam-spec.svg)](https://clojars.org/studiokalavati/sargam-spec) [![Build Status](https://travis-ci.com/Studio-kalavati/sargam-spec.svg?branch=master)](https://travis-ci.com/Studio-kalavati/sargam-spec)

# Sargam spec

This library is a Clojure/script [spec](https://clojure.org/guides/spec)ification to document Hindustani classical compositions in [Bhatkhande notation](http://www.swarsaptak.com/blog/blog-12/bhatkhande-swarlipi-or-notation-system) format. 

This library will also support serialization to (and from) JSON. 

Compositions can have parts (e.g Sthyai, Antara and multiple Taans) as well as the Taal the composition is set in, or it can be saved with a single part.

A couple of examples:

- A composition with [multiple parts](https://github.com/Studio-kalavati/sargam-spec/blob/master/resources/composition.json).
- A single [part](https://github.com/Studio-kalavati/sargam-spec/blob/master/resources/composition_part.json)

## Features

This spec supports the following features of the Bhatkhande notation

### Swara 

Swaras as denoted by `s`, `r`, `g`.. keywords. 

| Feature        | Example |
| -              | -       |
| Shuddha swaras | :s      |
| Komal swaras   | :-n     |
| Tivra swara    | :+m     |

Vishram swara `:-` and Avagraha swara is `:a` .

### Specifying the octave or saptak

Each note is described by a 2-tuple vector. The first element is the octave and the second is the swara
e.g. `[:madhyam :s]`

Supported saptaks are 
`:ati-mandra :mandra :madhyam :taar :ati-taar`.

### Notes per beat.

A single note per beat is specified as a vector of maps. 

- `[{:note [:madhyam :s]}]` indicates Sa held for the duration of the beat

- `[{:note [:madhyam :s]} {:note [:madhyam :s]}]` indicates two half/notes played for the duration of the beat. 

### Kan swara

Kan swara can be defined as an additional key in the swara map thus `{:note [:madhyam :s] :kan [:madhyam :r]}`.

### Meend

Meend is defined by adding a "meend-start" and "meend-end" key to any note. 

```
[{:note [:madhyam :r] :meend-start :true}] 
[{:note [:madhyam :r] :meend-end :true}]
```

### Taal definition

A Taal definition needs to have 

| Feature                                     |Description    | Example                        |
| -                                 |-               | -                              |
| :num-beats | Number of beats in a taal. Note that 1-based indexing is used, i.e 1-10, not 0-9. | 10                             |
| :id | Unique identifier for the taal, usually the name   | :jhaptaal   |
| :sam-khaali | the beat number for Sam and Khali, defined by a map where the key is the beat number, and the value is the sam/taali/khaali at that note     | {1 "x" 3 "2" 8 "4" 6 "o"} |
| :bhaags | Bhaags, defined as a vector where each ith value is the number of beats in the ith Bhaag | [2 3 2 3]                      |


Here's a complete example
```clj

(def jhaptaal {:id :jhaptaal 
               :bhaags [2 3 2 3]
               :sam-khaali {1 "x" 3 "2" 
                            8 "4" 6 "0"}
               :num-beats 10})
```


Specifying a taal section will automatically imply that the beats are to be synced with the taal that is defined.

## Contributions

Contributions welcome for 

- Taal definitions. Currently supported: Teentaal, Jhaptaal, Ektaal, Rupak, Dadra, Kehrwa.
- Multiple languages. Currently English and Devanagri are supported.

## License

Copyright © 2019 - 2023 Studio Kalavati

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.
