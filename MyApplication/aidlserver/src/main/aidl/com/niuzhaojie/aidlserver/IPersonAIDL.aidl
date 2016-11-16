package com.niuzhaojie.aidlserver;


import com.niuzhaojie.aidlserver.Person;

interface IPersonAIDL {

    List<Person> add(in Person person);

}
